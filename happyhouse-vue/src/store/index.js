import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginuser: null,
    // loginuser: {
    //   userId: "",
    //   userPassword: "",
    //   userName: "",
    //   userAddress: "",
    //   userPhoneNumber: "",
    // },
    preferLists: [],
  },
  getters: {},
  mutations: {
    SET_LOGIN_USER(state, loginuser) {
      state.loginuser = loginuser;
    },
    LOGOUT(state) {
      state.loginuser = null;
    },
    SET_PREFER_LIST(state, preferLists) {
      state.preferLists = preferLists;
    },
  },
  actions: {
    setLoginUser({ commit }, user) {
      commit("SET_LOGIN_USER", user);
    },
    logoutUser({ commit }) {
      commit("LOGOUT");
    },
    setPreferList({ commit }) {
      http
        .get(`/deal/prefer/${this.state.loginuser.userId}`)
        .then(({ data }) => {
          commit("SET_PREFER_LIST", data);
        });
    },
  },
  modules: {},
});
