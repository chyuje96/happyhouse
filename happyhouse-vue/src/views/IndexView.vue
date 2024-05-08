<template>
  <div class="wrapper">
    <parallax-comp class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>Happy House</h1>
            </div>
          </div>
        </div>
      </div>
    </parallax-comp>
    <main-map></main-map>
  </div>
</template>

<script>
import MainMap from '@/components/main/MainMap.vue';
export default {
  components: {
    MainMap,
  },
  name: 'IndexView',
  bodyClass: 'index-page',

  props: {
    image: {
      type: String,
      default: require('@/assets/img/architecture2.jpg'),
    },
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
    };
  },
  methods: {},
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
};
</script>
<style lang="scss">
@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
</style>
