<template>
  <div class="game-lobby">
    <div v-if=" name!=undefined">
      <h1>{{name}}</h1>
      <h2>User: {{user}}</h2>

      <h1>OVERLORD: </h1>
      <hr />
      <h2 v-if="gameLobby.overlord != null">{{gameLobby.overlord.name}}</h2>
      <hr />
      <h1>Heroes</h1>
      <hr />
      <h2 v-if="gameLobby.heroes != null" v-for="hero in gameLobby.heroes" :key="hero.name"> {{hero.name}}</h2>
      <hr />
    <div>
      <button>START GAME</button>
    </div>
    </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from "axios";

export default {
  name: "GameLobby",
  props: ["name"],

  data() {
    return {
      gameLobby: { name: "test" },
      user: this.$user
    };
  },
  mounted() {
    this.connect();
    axios
      .get("http://localhost:8080/lobbies/" + this.name)
      .then(response => {
        console.log("Success got all, received " + JSON.stringify(response));
        this.gameLobby = response.data;
      })
      .catch(error => {
        console.log("Received error: " + error);
      });
  },

  methods: {
    connect: function() {
      this.socket = new SockJS("http://localhost:8080/websocket-endpoint");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log("Frame received: " + frame);
          this.stompClient.subscribe("/topic/lobbies/"+ this.name, lobby => {
            console.log("Body: " + lobby.body);
            const received = JSON.parse(lobby.body);
            this.gameLobby = received;
          });
        },
        error => {
          console.log("Error: " + error);
          this.connected = false;
        }
      );
    }
  }
};
</script>
<style scoped>
* {
  text-align: center;
}

.player{
  color: green;
}
</style>
