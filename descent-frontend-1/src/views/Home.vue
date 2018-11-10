<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <LobbyOverview/>
  </div>
</template>

<script>
// @ is an alias to /src
import LobbyOverview from '@/components/LobbyOverview.vue'

export default {
  name: 'home',
  components: {
    LobbyOverview
  }
}
</script>
