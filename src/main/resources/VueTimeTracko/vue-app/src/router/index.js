
/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

// Composables
import { createRouter, createWebHistory } from 'vue-router/auto'
import { routes } from 'vue-router/auto-routes'
import LoginVue from '@/pages/LoginVue.vue';
import RegisterVue from '@/pages/RegisterVue.vue';
import MainVue from '@/pages/MainVue.vue';
import NotFoundVue from '@/pages/NotFoundVue.vue';
import WorkspaceVue from '@/pages/WorkspaceVue.vue';
import WorkspaceCreateVue from '@/pages/WorkspaceCreateVue.vue';
import WorkspaceViewVue from '@/pages/WorkspaceViewVue.vue';
import ProjectVue from '@/pages/ProjectVue.vue';
import ProjectCreateVue from '@/pages/ProjectCreateVue.vue';
import TaskListVue from '@/pages/TaskListVue.vue';
import AllTasksVue from '@/pages/AllTasksVue.vue';


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
      {
          path: '/',
          name: 'login',
          component: LoginVue,
          props: { showExtraBar: false}
          
      },
      {
        path: '/register',
        name: 'register',
        component: RegisterVue,
        props: { showExtraBar: false }
      },
      {
        path: '/main',
        name: 'main',
        component: MainVue,
        props: { showExtraBar: true }
      },
      {
        path: '/workspace',
        name: 'workspace',
        component: WorkspaceVue,
        props: { showExtraBar: true }
      },
      {
        path: '/workspace/create',
        name: 'workspace-create',
        component: WorkspaceCreateVue,
        props: { showExtraBar: true }
      },
      {
        path: '/workspace/project',
        name: 'workspace-project',
        component: ProjectVue,
        props: { showExtraBar: true }
      },
      {
        path: '/workspace/:id',
        name: 'workspace-view',
        component: WorkspaceViewVue,
        props: { showExtraBar: true }
      },
      {
        path: '/workspace/:id/projectcreate',
        name: 'workspace-project-create',
        component: ProjectCreateVue,
        props: { showExtraBar: true }
      },
      {
        path: '/workspace/:id/project/:pid',
        name: 'workspace-project-tasks',
        component: TaskListVue,
        props: { showExtraBar: true }
      },
      {
        path: '/alltasks',
        name: 'all-tasks',
        component: AllTasksVue,
        props: { showExtraBar: true }
      },
      {
        path: '/:catchAll(.*)',
        name: 'not-found',
        component: NotFoundVue,
        props: { showExtraBar: true }
      }
    ]
});

// Workaround for https://github.com/vitejs/vite/issues/11804
router.onError((err, to) => {
  if (err?.message?.includes?.('Failed to fetch dynamically imported module')) {
    if (!localStorage.getItem('vuetify:dynamic-reload')) {
      console.log('Reloading page to fix dynamic import error')
      localStorage.setItem('vuetify:dynamic-reload', 'true')
      location.assign(to.fullPath)
    } else {
      console.error('Dynamic import error, reloading page did not fix it', err)
    }
  } else {
    console.error(err)
  }
})

router.isReady().then(() => {
  localStorage.removeItem('vuetify:dynamic-reload')
})

export default router
