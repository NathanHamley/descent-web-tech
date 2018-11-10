<template>
  <div class="lobby">
    <table>
      <tr>
        <th>Name</th>
        <th>Overlords</th>
        <th>Players</th>
        <th colspan="2">Actions</th>
      </tr>
      <tr v-for="lobby in gameLobbies" :key="lobby.name">
        <td>{{lobby.name}}</td>
        <td>{{lobby.overlord.name}}</td>
        <td>
          <div v-for="hero in lobby.heroes" :key="hero.name">{{hero.name}} </div>
        </td>
        <td><button v-on:click="joinGame(lobby.name, false)">Join Hero</button></td>
        <td><button v-on:click="joinGame(lobby.name, true)">Join Overlord</button></td>
      </tr>
    </table>
    <label>Player Name: </label>
    <input type="text" v-model="playerName" /> <br />
    <label>New Game Name (Empty for Random)</label>
    <input type="text" v-model="gameName" />
    <button v-on:click="newGame(gameName)">New Game</button>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";

export default {
  name: "Lobby",
  data() {
    return {
      playerName: "test",
      gameName: "",
      gameLobbies: [
        {
          name: "Test",
          overlord: { name: "Evil" },
          heroes: [
            {
              name: "Savior"
            },
            {
              name: "CoolGuy"
            }
          ]
        }
      ],
      connected: false
    };
  },

  methods: {
    joinGame: function(gameName, overlord) {
      const joinObject = JSON.stringify(
        this.createJoinRequest(gameName, overlord)
      );
      console.log("Join game called: " + joinObject);
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send("/app-receive/joinGame", joinObject, {});
      }
      this.$user = this.playerName;
      this.$router.push('lobby/'+gameName);
    },
    newGame: function(gameName) {
      const newGameObject = JSON.stringify(this.createNewGame(gameName));
      console.log("New Game called: " + newGameObject);
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.send("/app-receive/createLobby", newGameObject, {});
      }
    },
    connect: function() {
      this.socket = new SockJS("http://localhost:8080/websocket-endpoint");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log("Frame received: " + frame);
          this.stompClient.subscribe("/topic/lobbies", lobby => {
            console.log("Received: " + lobby);
            console.log("Body: " + lobby.body);
            const received = JSON.parse(lobby.body);
            if (received.heroes === null) {
              received.heroes = [];
            }
            if (received.overlord == null) {
              received.overlord = {};
            }
            let existingIndex = this.findGameIndexWithName(received.name);
            if (existingIndex === null) {
              this.gameLobbies.push(received);
            } else {
              this.gameLobbies.splice(existingIndex, 1, received);
            }
          });
        },
        error => {
          console.log("Error: " + error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    createJoinRequest(gameName, overlord) {
      return {
        name: gameName,
        overlord: overlord ? this.playerName : null,
        players: overlord ? [] : [this.playerName]
      };
    },
    createNewGame(gameName) {
      return {
        name: gameName,
        overlord: null,
        players: null
      };
    },
    findGameIndexWithName(name) {
      for (let i = 0; i < this.gameLobbies.length; i++) {
        if (this.gameLobbies[i].name === name) {
          return i;
        }
      }
      return null;
    },
    sanitizeLobby(lobby) {
      console.log("Sanitizing: "+ lobby);
      if (lobby.heroes === null) {
        lobby.heroes = [];
      }
      if (lobby.overlord == null) {
        lobby.overlord = {};
      }
      return lobby;
    }
  },
  mounted() {
    this.connect();
    axios
      .get("http://localhost:8080/lobbies/all")
      .then(response => {
        console.log("Success got all, received " + JSON.stringify(response));
        this.gameLobbies = [];
        for(var i = 0;i<response.data.length;i++){
          this.gameLobbies.push(this.sanitizeLobby(response.data[i]));
        }
      })
      .catch(error => {
        console.log("Received error: " + error);
      });
  },
  beforeDestroy() {
    console.log("calling before Destroy");
    this.disconnect();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
table {
  border: solid 1px black;
  margin: auto;
  padding: 10px;
  width: 50%;
}
th,
td {
  border: solid 1px black;
  margin: auto;
  padding: 10px;
}
</style>
