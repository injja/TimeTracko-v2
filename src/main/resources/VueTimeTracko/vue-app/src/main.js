/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'
import "primeicons/primeicons.css";
import router from '@/router';

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'

const app = createApp(App)
registerPlugins(app)
app.mount('#app')
