<template>
  <v-img
        :src=logo
        max-height="30"
        max-width="30"
        class="dimmed-img mx-5"
    ></v-img>
    <v-toolbar-title>TimeTracko</v-toolbar-title>
    <v-spacer></v-spacer>
    <RouterLink style="text-decoration: none; color: inherit;" to="/alltasks"> <v-btn text v-if="showExtraBar">My Tasks</v-btn></RouterLink>
    <RouterLink style="text-decoration: none; color: inherit;" to="/workspace"> <v-btn text v-if="showExtraBar">Home</v-btn></RouterLink>
    <v-btn :loading="logout_loading" @click="logout" text v-if="showExtraBar">Logout</v-btn>

</template>

<script setup>
  import { ref, onMounted, defineProps } from 'vue'
  import logo from '@/assets/Tlogo.png'
  
  defineProps ({
  showExtraBar: {
    type: Boolean,
    default: false
  }
})


const logout_loading = ref(false)

  const logout = async () => {
    console.log(localStorage.getItem("auth"))
    localStorage.removeItem("auth")
    localStorage.removeItem("uname")
    localStorage.removeItem("upasswd")
    console.log(localStorage.getItem("auth"))

    logout_loading.value = true
    setTimeout(() => {logout_loading.value = false}, 1000)
    setTimeout(() => (window.location.href = '/'), 950)

  }
</script>