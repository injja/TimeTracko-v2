<template>
    <v-app-bar app color="primary">
        <NavBar/>
    </v-app-bar>  
    <v-main app>
        <v-card class="mx-auto my-5" width="400">
            <v-card-title>
                <h1 class="display-1">Page not found</h1>
            </v-card-title>
            <v-card-text>
                <RouterLink to="/" ><v-btn variant="elevated" color="info">Go to Login Page</v-btn></RouterLink>
            </v-card-text>
                
                
        </v-card>
  </v-main>
</template>

<script setup>
  import { RouterLink } from 'vue-router'
  import Main from '@/components/Main.vue'
</script>
