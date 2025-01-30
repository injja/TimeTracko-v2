<template>
  <v-app-bar app color="primary">
    <NavBar/>
  </v-app-bar>

  <v-main app>
    <v-card class="mx-auto my-5" width="400">
          <v-card-title>
            <h1 class="display-1">Login</h1>
          </v-card-title>
          <v-card-text>
            <form @submit.prevent="submit">
              <v-text-field
                color="info"
                v-model="username.value.value"
                :error-messages="username.errorMessage.value"
                label="Username"
                prepend-icon="mdi-account-circle"
              />
              <v-text-field
                color="info" 
                v-model="password.value.value"
                :error-messages="password.errorMessage.value"
                :type="showPassword ? 'text' : 'password'"
                label="Password"
                prepend-icon="mdi-lock"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append="showPassword = !showPassword"
              />
              <v-row >
                <v-col>
                  <RouterLink to="/register" ><v-btn variant="elevated" color="success">Register</v-btn></RouterLink>
                </v-col>
                
                <v-col class="d-flex justify-end">
                  <v-btn 
                  type="submit" variant="elevated" color="info" :loading="loading" @click="load"
                  >Login</v-btn>
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
import { RouterLink } from 'vue-router'

defineProps ({
  showExtraBar: {
    type: Boolean,
  }
})

const showPassword = ref(false)
const url = "http://localhost:8080";
const login = ref('')
const loading = ref(false)

const { handleSubmit, handleReset } = useForm({
    validationSchema: {
      username (value) {
        if (value?.length >= 3) return true

        return 'Username needs to be at least 3 characters.'
      },
      password (value) {
        if (value?.length >= 3) return true

        return 'Password needs to be at least 3 characters.'
      }
    },
  })

  const username = useField('username')
  const password = useField('password')

  const submit = handleSubmit(values => {
    axios.get(url, {
      auth: {
        username: values.username,
        password: values.password
      }
    })
    .then(response => {
      console.log("tak");
      login.value = `Basic ${btoa(values.username + ':' + values.password)}`
      console.log(login.value);
      localStorage.setItem("auth", login.value)
      localStorage.setItem("uname", values.username)
      localStorage.setItem("upasswd", values.password)
      setTimeout(() => (window.location.href = '/workspace'), 1000)
      
      // response.data.key
    })
    .catch(error => {
      console.log("nie");
      console.error('Error:', error);
    })
  })

  const load = async () => {
    loading.value = true
    setTimeout(() => (loading.value = false), 1000)
  }
  // const username = ref('');
  // const password = ref('');
  // const status = ref(0);
  // const elogin = ref('');




//  const login = async () => {
//   const response = await axios.get(url, {
//     auth: {
//       username: username.value,
//       password: password.value
//     }
//   })
//   .catch(error => {
//     status.value = 0;
//     console.log("nie", status.value);
//     console.error('Error:', error);
//   });
//   status.value = 1;
//   console.log("tak", status.value);
// }

// axios.post('http://example.com/api/endpoint', {
//     // Request payload
//     key1: 'value1',
//     key2: 'value2'
// }, {
//     auth: {
//         username: username,
//         password: password
//     }
// })
// .then(response => {
//     console.log('Success:', response.data);
// })
// .catch(error => {
//     console.error('Error:', error);
// });


const reg = async () => {
    // const jsonData = { name: 'admin' };
    
    // const authHeader = `Basic ${btoa(username.value + ':' + password.value)}`; // Basic Authentication header
    
    // try {
    //     // Await the response
    //     const response = await axios.post(
    //         'http://localhost:8080/role',
    //         jsonData,
    //         {
    //             headers: {
    //                 'Content-Type': 'application/json', // Set Content-Type to application/json
    //                 'Authorization': authHeader,        // Basic Authentication header
    //             }
    //         }
    //     );
        
    //     // Log the response to see what we got
    //     console.log('Full Response:', response); // Logs entire response object

    //     // Access the data from the response
    //     if (response.data) {
    //         console.log("ID:", response.data.id); // Logs the ID
    //         console.log("Name:", response.data.name); // Logs the name
    //     } else {
    //         console.error('No data in response:', response);
    //     }
    // } catch (error) {
    //     console.error('Error making the POST request', error.message);
    // }
// const jsonData = {
//   name: "fname.value",
//   surname: "fsurname.valeue",
//   username: "fusername.value",
//   password: 'fpassword.value',
//   email: "femail.value",
// };

// const response = await axios.post(
//         'http://localhost:8080/register',
//         jsonData,
//         {
//             headers: {
//                 'Content-Type': 'application/json',}
//         }
//     )
//     .then(response => {
//         console.log("tak");
//         console.log(response.data);
//      })
//      .catch(error => {
//         console.log("nie");
//         console.error('Error:', error);
//      });

}

</script>
