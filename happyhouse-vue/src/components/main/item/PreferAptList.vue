<template>
  <div class="md-scrollbar">
    <md-list>
      <md-list-item md-expand>
        <md-card class="card">
          <!-- 아파트명(로그인O)-->
          <md-card-content :md-expand-single="expandSingle">관심 아파트 목록</md-card-content>
        </md-card>
        <md-list slot="md-expand">
          <md-list-item
            class="md-elevation-1"
            v-for="(houseinfo, prefer) in preferLists"
            :key="prefer.no"
          >
            <p @click="preferclicked(houseinfo)">{{ houseinfo.apartmentName }}</p>
            <div>
              <md-button class="md-raised" @click="deletePrefer(prefer)">삭제</md-button>
            </div></md-list-item
          >
        </md-list>
      </md-list-item>
    </md-list>
  </div>
</template>

<script>
/*
Preferred [no=9,
sidoName=2700000000,
gugunName=2711000000,
dongName=2711010100,
dongCode=2711010100,
userId=ssafy,
aptCode=27110000000035]
*/
import { mapState, mapActions } from 'vuex';
import http from '@/api/http';
export default {
  name: 'preferAptList',
  data() {
    return {
      selprefer: null,
    };
  },
  computed: {
    ...mapState(['loginuser', 'preferLists']),
  },
  created() {
    this.setPreferList();
  },
  methods: {
    ...mapActions(['setPreferList']),
    preferclicked(houseinfo) {
      this.selprefer = houseinfo;
      console.log(this.selprefer);
      this.$emit('update:selpre', this.selprefer);
    },
    deletePrefer(prefer) {
      let preferMap = this.splitPrefer(prefer);
      http.delete(`/deal/prefer/${preferMap.no}`).then(({ data }) => {

        this.setPreferList();
      });
    },
    splitPrefer(prefer) {
      let splitPrefer = prefer.split(',');
      splitPrefer[0] = splitPrefer[0].split(' ')[1].replace('[', '');
      let len = splitPrefer.length;
      splitPrefer[len - 1] = splitPrefer[len - 1].replace(']', '');

      let preferMap = {};
      for (const elem of splitPrefer) {
        let splitelem = elem.split('=');
        preferMap[splitelem[0].trim()] = splitelem[1].trim();
      }
      return preferMap;
    },
  },
};
</script>

<style></style>
