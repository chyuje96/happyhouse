<template>
  <div id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <main-navbar />
      <div>
        <router-view />
      </div>
      <main-footer />
    </div>
  </div>
</template>

<script>
import MainFooter from './layout/MainFooter.vue';
import MainNavbar from './layout/MainNavbar.vue';
export default {
  name: 'main',
  components: {
    MainNavbar,
    MainFooter,
  },
};
</script>
