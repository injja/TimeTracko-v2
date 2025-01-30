<template>
    <v-app-bar app color="primary">
        <NavBar :showExtraBar="true"/>
    </v-app-bar>
        
    <v-main app>
        <RouterLink style="text-decoration: none; color: inherit;" to="/workspace/create"> <v-btn text>Create your own Workspace</v-btn></RouterLink>
        <v-container class="fill-height">
      <v-responsive
        class="align-centerfill-height mx-auto"
        max-width="900"
      >

        <div class="text-center">
          <div class="text-body-2 font-weight-light mb-n1">Welcome to</div>
          <h1 class="text-h2 font-weight-bold">Your Workspaces</h1>

        </div>
        <div class="py-4"></div>

        <v-row>
          <v-col cols="6" v-for="workspace in workspaces" :key="workspace.id">
            
            <RouterLink style="text-decoration: none; color: inherit;" :to="'/workspace/' + workspace.id"><v-card
              append-icon="mdi-open-in-new"
              class="py-4"
              color="surface-variant"
              prepend-icon="mdi-widgets-outline"
              rel="noopener noreferrer"
              rounded="lg"
              :subtitle="workspace.description"
              :title="workspace.title"
              variant="text"
            >
              <v-overlay
                opacity=".06"
                scrim="primary"
                contained
                model-value
                persistent
              />
            </v-card></RouterLink>
          </v-col>
        </v-row>
      </v-responsive>
    </v-container>
    </v-main>
</template>
  
<script setup>
  import { ref, onMounted, defineProps } from 'vue'
  import { useField, useForm } from 'vee-validate'
  import axios from 'axios'
  import NavBar from '@/components/NavBar.vue'
  import logo from '@/assets/Tlogo.png'
  import { RouterLink } from 'vue-router'
  defineProps ({
    showExtraBar: {
      type: Boolean,
    }
  })

  const login = ref('')
  const response = ref('')
  const url = "http://localhost:8080/workspace";
  const workspaces = ref('')
  
  onMounted(() => {
    console.log(localStorage.getItem("auth"))
    
    login.value = JSON.stringify(login.value, null ,2)
    if (login.value == null) {
        window.location.href = '/404'
    }
    else {
        axios.get(url, {
        auth: {
            username: localStorage.getItem("uname"),
            password: localStorage.getItem("upasswd")
        }
        })
        .then(response => {
            console.log("tak");
            workspaces.value = response.data;
        
        // response.data.key
        })
        .catch(error => {
            console.log("nie");
            console.error('Error:', error);
        })
    }
  })


</script>
