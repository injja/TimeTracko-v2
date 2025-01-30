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
    <RouterLink style="text-decoration: none; color: inherit;" to="/main"> <v-btn text v-if="showExtraBar">Home</v-btn></RouterLink>
    <RouterLink style="text-decoration: none; color: inherit;" to="/" ><v-btn text v-if="showExtraBar">Logout</v-btn></RouterLink>
  </v-app-bar>


  <v-main app>
    <v-card class="mx-auto my-5" width="400">
      <v-card-title>
        <h1 class="display-1">Register</h1>
      </v-card-title>
      <v-card-text>
        <form @submit.prevent="submit">
              <v-text-field
                color="info"
                v-model="name.value.value"
                :error-messages="name.errorMessage.value"
                label="Name"
                prepend-icon="mdi-account-circle"
              />
              <v-text-field
                color="info"
                v-model="surname.value.value"
                :error-messages="surname.errorMessage.value"
                label="Surname"
                prepend-icon="mdi-account-circle"
              />
              <v-text-field
                color="info"
                v-model="username.value.value"
                :error-messages="username.errorMessage.value"
                label="Username"
                prepend-icon="mdi-account-circle"
              />
              <v-text-field
                color="info"
                v-model="email.value.value"
                :error-messages="email.errorMessage.value"
                label="Email"
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
                  <RouterLink to="/" ><v-btn variant="elevated" color="red-darken-1">Back</v-btn></RouterLink>
                </v-col>
                
                <v-col class="d-flex justify-end">
                  <v-btn type="submit" variant="elevated" color="info" :loading="loading" @click="load">Register</v-btn>
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
const url = "http://localhost:8080/register";
const login = ref('')
const loading = ref(false)


const { handleSubmit, handleReset } = useForm({
    validationSchema: {
      username (value) {
        if (value?.length >= 3) return true

        return 'Username needs to be at least 3 characters.'
      },
      name (value) {
        if (value?.length >= 3) return true

        return 'Name needs to be at least 3 characters.'
      },
      surname (value) {
        if (value?.length >= 3) return true

        return 'Surname needs to be at least 3 characters.'
      },
      password (value) {
        if (value?.length >= 3) return true

        return 'Password needs to be at least 3 characters.'
      },
      email (value) {
        if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

        return 'Must be a valid e-mail.'
      }
    },
  })

  const name = useField('name')
  const surname = useField('surname')
  const username = useField('username')
  const password = useField('password')
  const email = useField('email')


  const submit = handleSubmit(values => {

    axios.post(
        'http://localhost:8080/register',
        {
          name: values.name,
          surname: values.surname,
          username: values.username,
          password: values.password,
          email: values.email,
        },
        {
          headers: {
            'Content-Type': 'application/json',}
        }
    )
    .then(response => {
        console.log("tak");
        setTimeout(() => (window.location.href = '/'), 1000)
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

/**
 * {
    "name": "arek",
    "admin": 1,
    "username": "arek",
    "password": "arek",
    "surname": "arek",
    "email": "arek",
}
 */


</script>
