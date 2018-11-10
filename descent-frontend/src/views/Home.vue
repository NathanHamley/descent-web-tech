<template>
  <div class="home">
    <h1>Welcome to Descent</h1>
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
