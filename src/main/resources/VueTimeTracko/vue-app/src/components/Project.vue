<template>
  <v-app-bar app color="primary">
      <NavBar :showExtraBar="true"/>
  </v-app-bar>
      
  <v-main app>
      
      <v-container class="fill-height">
    <v-responsive
      class="align-centerfill-height mx-auto"
      max-width="900"
    >

      <div class="text-center">
        <div class="text-body-2 font-weight-light mb-n1">Welcome to</div>
        <h1 class="text-h2 font-weight-bold">Your Projects</h1>

      </div>
      <div class="py-4"></div>

      <v-row>

        <v-col cols="6">
          <RouterLink style="text-decoration: none; color: inherit;" :to="'/workspace/'+info.id+'/projectcreate'"> <v-btn text v-if="showExtraBar">Create your own Project</v-btn></RouterLink>
          <v-card
            append-icon="mdi-open-in-new"
            class="py-4"
            color="surface-variant"
            prepend-icon="mdi-widgets-outline"
            rel="noopener noreferrer"
            rounded="lg"
            subtitle="'Author: ' + info.createdBy.user.name + ' ' + info.createdBy.user.surname"
            title="info.title"
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