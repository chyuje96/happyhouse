<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>Q&A 게시판</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="main main-raised">
      <md-table class="list-table" v-model="articles" md-sort="no" md-sort-order="desc" md-card>
        <md-table-toolbar> </md-table-toolbar>

        <md-table-row slot="md-table-row" slot-scope="{ item }">
          <md-table-cell md-label="글 번호" md-sort-by="no">{{ item.no }}</md-table-cell>
          <md-table-cell md-label="제목" md-sort-by="title">
            <router-link :to="{ name: 'QnaDetail', params: { no: item.no } }">{{
              item.title
            }}</router-link>
          </md-table-cell>
          <md-table-cell md-label="작성자" md-sort-by="userId">{{ item.userId }}</md-table-cell>
          <md-table-cell md-label="등록일" md-sort-by="regtime">{{
            item.regtime | dateFormat
          }}</md-table-cell>
        </md-table-row>
      </md-table>
      <div class="md-raised btn-margin">
        <div v-if="loginuser">
          <md-button class="md-fab-bottom-right" @click="moveWrite()">글쓰기</md-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/api/http';
import moment from 'moment';
import { mapState} from 'vuex';

export default {
  bodyClass: 'profile-page',
  name: 'QnaList',
  data() {
    return {
      articles: [],
      target: '',
    };
  },

  props: {
    image: {
      type: String,
      default: require('@/assets/img/architecture2.jpg'),
    },
  },

  computed: {
    ...mapState(['loginuser']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
  created() {
    http.get(`/qna`).then(({ data }) => {
      this.articles = data;
    });

    if (this.type === 'login') {
      this.loginuser.userId = this.$store.state.loginuser.userId;
    }
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: 'QnaRegister' });
    },
    viewArticle() {
      this.$router.push({
        name: 'QnaDetail',
        params: { no: this.target },
      });
    },
    updatePagination(page, pageSize, sort, sortOrder) {
      // console.log('pagination has updated', page, pageSize, sort, sortOrder);
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format('YY.MM.DD');
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*='tab-pane-'] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}

#qnaTitle {
  color: black !important;
  font-weight: 600;
  font-size: 24px;
  text-align: center;
  display: initial;
}

.md-title {
  font-weight: 600;
}

.md-card {
  font-weight: 600;
  margin-top: 0px;
  box-shadow: 0 0px 0px 0 rgb(0 0 0 / 14%), 0 0px 0px 0px rgb(0 0 0 / 20%),
    0 0px 0px 0 rgb(0 0 0 / 12%); // 마지막에 수정해야함
}

.md-fab-bottom-right {
  margin: 10px 6px;
  width: 94%;
}

.btn-margin {
  margin-left: 40%;
  margin-right: 40%;
}

.main main-raised {
  width: 75%;
}

.list-table {
  width: 90%;
  margin-right: 0%;
  margin-left: 10%;
}

.brand {
  color: #ffffff;
}

h1 {
  font-weight: 550 !important;
}

.md-theme-default a:not(.md-button) {
  color: black !important;
}
</style>
