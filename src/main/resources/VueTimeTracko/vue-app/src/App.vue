<template>
  <v-app>
    <!-- <v-app-bar app color="primary">
      <NavBar :showExtraBar="true"/>
    </v-app-bar>
      
    <v-main app>
    </v-main> -->
    <RouterView></RouterView>

    <v-footer app color="primary">
      <Footer />
    </v-footer>

  </v-app>
</template>


<script setup>
import { ref, onMounted, defineProps } from 'vue'
import { RouterView } from 'vue-router';
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'

// defineProps(['showExtraBar'])

defineProps ({
  username: {
    type: String,
    default: ''
  },
  showExtraBar: {
    type: Boolean,
    default: true
  }
})


</script>
