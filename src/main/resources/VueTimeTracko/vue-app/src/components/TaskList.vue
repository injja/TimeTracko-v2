<template>
    <v-app-bar app color="primary">
        <NavBar :showExtraBar="true"/>
    </v-app-bar>
        
    <v-main app>
        <RouterLink style="text-decoration: none; color: inherit;" :to="'/workspace/'+workspaceId+'/projectcreate'"> <v-btn text >Create Project</v-btn></RouterLink>
            
        <v-container class="fill-height">
      <v-responsive
        class="align-centerfill-height mx-auto"
        max-width="900"
      >

        <div class="text-center">

          <div class="text-body-2 font-weight-light mb-n1">Welcome to</div>
          <h1 class="text-h2 font-weight-bold">Your Tasks</h1>

        </div>
        <div class="py-4"></div>

        <v-row>

          <v-col cols="6" v-for="info in projects" :key="info.id">
            <v-card
              append-icon="mdi-open-in-new"
              class="py-4"
              color="surface-variant"
              prepend-icon="mdi-widgets-outline"
              rel="noopener noreferrer"
              rounded="lg"
              :subtitle=info.description
              :title=info.title
              variant="text"
            >
              <v-overlay
                opacity=".06"
                scrim="primary"
                contained
                model-value
                persistent
              />
            </v-card>
          </v-col>
        </v-row>
      </v-responsive>
    </v-container>
    </v-main>
</template>
  
<script setup>
  import { ref, onMounted, defineProps, reactive} from 'vue'
  import { useField, useForm } from 'vee-validate'
  import axios from 'axios'
  import NavBar from '@/components/NavBar.vue'
  import logo from '@/assets/Tlogo.png'
  import { RouterLink, useRoute } from 'vue-router'

  defineProps ({
    showExtraBar: {
      type: Boolean,
    },
    id: {}
    
  })

  const route = useRoute();

  const workspaceId = route.params.id

  const login = ref('')
  const response = ref('')
  const url = `http://localhost:8080/projects/workspace/${workspaceId}`;
  console.log(url)

  const projects = ref('')
  
  onMounted(() => {
    
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
            projects.value = response.data;
        
        // response.data.key
        })
        .catch(error => {
            console.log("nie");
            console.error('Error:', error);
        })
    }
  })


</script>
