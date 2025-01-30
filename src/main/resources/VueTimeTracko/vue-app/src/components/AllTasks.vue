<template>
    <v-app-bar app color="primary">
        <NavBar :showExtraBar="true"/>
    </v-app-bar>
        
    <v-main app><v-container class="fill-height">
      <v-responsive
        class="align-centerfill-height mx-auto"
        max-width="900"
      >

        <div class="text-center">
          <div class="text-body-2 font-weight-light mb-n1">Welcome to</div>
          <h1 class="text-h2 font-weight-bold">All Tasks</h1>

        </div>
        <div class="py-4"></div>

        <v-row>
            <v-col cols="12" v-if="showFree">
            <v-card
              class="py-4 text-center"
              color="surface-variant"
              prepend-icon="mdi-rocket-launch-outline"
              rounded="lg"
              variant="outlined"
            >
              <template #image>
                <v-img position="top right" />
              </template>

              <template #title>
                <h2 class="text-h5 font-weight-bold">No Tasks!</h2>
              </template>

              <v-overlay
                opacity=".12"
                scrim="primary"
                contained
                model-value
                persistent
              />
            </v-card>
          </v-col>

          <v-col cols="6" v-for="task in tasks" :key="workspace.id">


            
            <RouterLink style="text-decoration: none; color: inherit;" :to="'/workspace/' + task.id"><v-card
              append-icon="mdi-open-in-new"
              class="py-4"
              color="surface-variant"
              prepend-icon="mdi-widgets-outline"
              rel="noopener noreferrer"
              rounded="lg"
              :subtitle="task.description"
              :title="task.title"
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
            <v-card
              append-icon="mdi-open-in-new"
              class="py-4"
              color="surface-variant"
              prepend-icon="mdi-widgets-outline"
              rel="noopener noreferrer"
              rounded="lg"
              :subtitle="task.description"
              :title="task.title"
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
  const url = "http://localhost:8080/task";
  const tasks = ref('')
  const showFree = ref(false)
  
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
            if ((tasks.value).length== 0) {
                showFree.value = true
            }

            console.log("tak");
            tasks.value = response.data;
            console.log((tasks.value).length);


        
        // response.data.key
        })
        .catch(error => {
            console.log("nie");
            console.error('Error:', error);
        })
    }
  })


</script>
