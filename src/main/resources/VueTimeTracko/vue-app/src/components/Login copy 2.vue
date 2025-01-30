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
  try {
      const response = await axios.get(url, {
          auth: {
              username: username.value,
              password: password.value
          }
      });
      
      // Handle the response, check if 'message' exists
      if (response.data && response.data.message) {
          status.value = 1;
          elogin.value = btoa(username.value + ":" + password.value);
          console.log("Login successful:", status.value, elogin.value);
          console.log("Response Message:", response.data.message);
      } else {
          console.error('No message in response:', response);
      }
  } catch (error) {
      status.value = 0;
      console.error('Login failed:', error);
  }
};

onMounted(async () => {
  const jsonData = { name: 'admin' };
  
  const authHeader = `Basic ${btoa("arek" + ':' + "arek")}`; // Basic Authentication header
  
  try {
      // Await the response
      const response = await axios.post(
          'http://localhost:8080/role',
          jsonData,
          {
              headers: {
                  'Content-Type': 'application/json', // Set Content-Type to application/json
                  'Authorization': authHeader,        // Basic Authentication header
              }
          }
      );
      
      // Handle the response (Check if 'message' exists)
      if (response.data && response.data.message) {
          console.log("Message from response:", response.data.message);
      } else {
          console.error('No message in response:', response);
      }
  } catch (error) {
      console.error('Error making the POST request', error);
  }
})
</script>
