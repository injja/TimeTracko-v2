<template>
    <v-app-bar app color="primary">
      <v-img
          :src=logo
          max-height="30"
          max-width="30"
          class="dimmed-img mx-5"
      ></v-img>
      <v-toolbar-title>TimeTracko</v-toolbar-title>
      <v-spacer></v-spacer>
      <RouterLink style="text-decoration: none; color: inherit;" to="/workspace"> <v-btn text v-if="showExtraBar">Home</v-btn></RouterLink>
      <RouterLink style="text-decoration: none; color: inherit;" to="/" ><v-btn text v-if="showExtraBar">Logout</v-btn></RouterLink>
    </v-app-bar>
  
  
    <v-main app>
      <v-card class="mx-auto my-5" width="400">
        <v-card-title>
          <h1 class="display-1">Create Project</h1>
        </v-card-title>
        <v-card-text>
          <form @submit.prevent="submit">
                <v-text-field
                  color="info"
                  v-model="name.value.value"
                  :error-messages="name.errorMessage.value"
                  label="Project's Name"
                  prepend-icon="mdi-account-circle"
                />
                <v-text-field
                  color="info"
                  v-model="description.value.value"
                  :error-messages="description.errorMessage.value"
                  label="Project's Description"
                  prepend-icon="mdi-account-circle"
                />
                <v-row >
                  <v-col>
                    <RouterLink :to="'/workspace/'+workspaceId" ><v-btn variant="elevated" color="red-darken-1">Back</v-btn></RouterLink>
                  </v-col>
                  
                  <v-col class="d-flex justify-end">
                    <v-btn type="submit" variant="elevated" color="info" :loading="loading" @click="load">Create</v-btn>
                  </v-col>
                </v-row>
                
              </form>
        </v-card-text>
      </v-card>
    </v-main>
  </template>
  
  <script setup>
  import { ref, onMounted, defineProps } from 'vue'
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
  
  
  const showPassword = ref(false)
  const url = "http://localhost:8080/workspace";
  const login = ref('')
  const loading = ref(false)
  const route = useRoute();
  const workspaceId = route.params.id
  
  
  const { handleSubmit, handleReset } = useForm({
      validationSchema: {
        name (value) {
          if (value?.length >= 5) return true
  
          return 'Name needs to be at least 5 characters.'
        },
        description (value) {
          if (value?.length >= 5) return true
  
          return 'Description needs to be at least 5 characters.'
        }
      },
    })
    const response = ref('')
    const name = useField('name')
    const description = useField('description')

    const submit = handleSubmit(values => {
      console.log(localStorage.getItem("auth"))
    
      login.value = JSON.stringify(login.value, null ,2)
      if (login.value == null) {
          window.location.href = '/404'
      }
      else {
        const requestBody = ({
        name: values.name,
        description: values.description,
        workspace_id: workspaceId
        });

        console.log(requestBody);

        axios.post(url,
        requestBody,
        {
            auth: {
                username: localStorage.getItem("uname"),
                password: localStorage.getItem("upasswd")
            },
            headers: {
                'Content-Type': 'application/json',
            }
        }
    )
    .then(response => {
        console.log("tak");
        console.log(response);
        // window.location.href = `/workspace/${workspaceId.value}`
     })
     .catch(error => {
        console.log("nie");
        console.error('Error:', error);
     })
      } 
    })
  
  
    const load = async () => {
      loading.value = true
      setTimeout(() => (loading.value = false), 1000)
    }
  

  
  
  </script>
  