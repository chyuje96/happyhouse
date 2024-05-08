<template>
  <footer class="footer" :data-background-color="backgroundColor">
    <div class="container">
      <div class="copyright">
        &copy; {{ year }}, made with <md-icon>favorite</md-icon> by H & Y for a better web.
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String,
  },
  data() {
    return {
      year: new Date().getFullYear(),
    };
  },
};
</script>
<style>
.footer .container {
  justify-content: center;
}
</style>
