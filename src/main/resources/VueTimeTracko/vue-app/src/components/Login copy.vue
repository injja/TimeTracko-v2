<template>
    <v-card class="mx-auto my-5" width="400">
          <v-card-title>
            <h1 class="display-1">Login</h1>
          </v-card-title>
          <v-card-text>
            <v-form @keydown.enter="login">
              <v-text-field
                color="info"
                v-model="username"
                label="Username"
                prepend-icon="mdi-account-circle"
              />
              <v-text-field
                color="info" 
                v-model="password"
                :type="showPassword ? 'text' : 'password'"
                label="Password"
                prepend-icon="mdi-lock"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append="showPassword = !showPassword"
              />
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-btn variant="elevated" color="success">Register</v-btn>
            <v-spacer></v-spacer>
            <v-btn @click="login" variant="elevated" color="info">Login</v-btn>
          </v-card-actions>
      </v-card>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import logo from '@/assets/Tlogo.png'

const showPassword = ref(false)
const url = "http://localhost:8080";
const username = ref('');
const password = ref('');
const status = ref(0);
const elogin = ref('');

const login = async () => {
  axios.get(url, {
    auth: {
      username: username.value,
      password: password.value
    }
  })
  .then(response => {
    status.value = 1;
    elogin.value = btoa(username.value + ":" + password.value)
    console.log("tak", status.value, elogin.value);
    // response.data.key
  })
  .catch(error => {
    status.value = 0;
    console.log("nie", status.value);
    console.error('Error:', error);
  });
}


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


onMounted(() => {
//   const url2 = 'http://localhost:8080/role'

  const jsonData = {
  name: 'admin',
};
//    axios.post(url2, {
//     auth: {
//       username: "arek",
//       password: "arek"
//     }
//   }, jsonData, 
//   {
//   headers: {
//     'Content-Type': 'application/json'
//   }
//   }
// )
// .then(response => {
//     status.value = 1;
//     elogin.value = btoa(username.value + ":" + password.value)
//     console.log(response.data);
//     // response.data.key
//   })
const authHeader = `Basic ${btoa("arek" + ':' + "arek")}`; // Basic Authentication header
    
    try {
      const response = axios.post(
        'http://localhost:8080/role',
        jsonData,
        {
          headers: {
            'Content-Type': 'application/json', // Set Content-Type to application/json
            'Authorization': authHeader,        // Basic Authentication header
          },
        }
      );
      console.log(response.data.message)
    } catch (error) {
      console.error('Error making the POST request', error);
      setResponseMessage('An error occurred while sending data');
    }


})



</script>
