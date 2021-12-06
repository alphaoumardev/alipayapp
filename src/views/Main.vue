<template>
  <div>
    <el-container class="main-container">
      <el-header class="el-header">
        <div class="left-box">
          <img src="../assets/img/timg.gif" alt="aa">
          <span>Covid-19 MS</span>
        </div>
        <div>
          <el-menu
            :dafault-active="$route.path"
            mode="horizontal"
            class="el-menu-demo"
            background-color="#545c64"
            text-color="white"
            :router="true"
            menu-trigger="hover">

            <template class="dow" v-for="item in menuList" :key="item.id">
              <el-submenu class="dow" :index="item.id+''" :desabled="item.desabled" v-if="item.children.length>0">
                <template #title>
                  <i :class="item.icon"/><span>{{ item.menuName }}</span>
                </template>
                <MainTree :menuList="item.children"/>
              </el-submenu>
              <el-menu-item class="dow" v-else :index="item.id" :desabled="item.desabled" :route="item.url+''"
                            @click="path(item.url)">
                <i :class="item.icon"/>
                <span>{{ item.menuName }}</span>
              </el-menu-item>

            </template>
          </el-menu>
        </div>

        <div class="right-box">
          <el-dropdown>
            <img src="../assets/img/timg.gif" alt="Aj">
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item><i class="el-icon-house"/>Home</el-dropdown-item>
                <el-dropdown-item><i class="el-icon-microphone"/>交流</el-dropdown-item>
                <el-dropdown-item><i class="el-icon-switch-button"/>Logout</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <!--      <img src="../assets/img/logo4.png" alt="alpha"/>-->

      <el-container>

        <el-main>
          <router-view/>
        </el-main>
      </el-container>

      <Footer/>
    </el-container>
  </div>
</template>

<script>
import MainTree from "@/components/MainTree";
import Footer from "../components/Footer";

export default {
  name: "Main",
  components:
    {
      Footer,
      MainTree
    },
  data() {
    return {
      isCallapse: false,
      activePath: '',
      menuList:
        [
          {
            "id": 1,
            "parentId": 0,
            "menuName": "Management",
            "url": "",
            "icon": "el-icon-setting",
            "orderNum": 1,
            "open": 1,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 253,
                "parentId": 1,
                "menuName": "Welcome",
                "url": "/welcome",
                "icon": "el-icon-star-off",
                "orderNum": 1,
                "open": 0,
                "disabled": false,
                "perms": "welcome:view",
                "type": 0,
                "children": []
              },
              {
                "id": 226,
                "parentId": 1,
                "menuName": "Users",
                "url": "/users",
                "icon": "el-icon-user",
                "orderNum": 2,
                "open": 0,
                "disabled": false,
                "perms": "users",
                "type": 0,
                "children": []
              },
              {
                "id": 321,
                "parentId": 1,
                "menuName": "Attachements",
                "url": "/attachments",
                "icon": "el-icon-picture-outline",
                "orderNum": 2,
                "open": 1,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              },
              {
                "id": 4,
                "parentId": 1,
                "menuName": "Permissions",
                "url": "/menus",
                "icon": "el-icon-help",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": null,
                "type": 0,
                "children": []
              },
              {
                "id": 235,
                "parentId": 1,
                "menuName": "Roles",
                "url": "/roles",
                "icon": "el-icon-postcard",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              },
              {
                "id": 261,
                "parentId": 1,
                "menuName": "Department",
                "url": "/departments",
                "icon": "el-icon-s-home",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              },
              {
                "id": 319,
                "parentId": 1,
                "menuName": "Notifications",
                "url": "/notices",
                "icon": "el-icon-s-flag",
                "orderNum": 4,
                "open": 0,
                "disabled": true,
                "perms": "",
                "type": 0,
                "children": []
              }
            ]
          },
          {
            "id": 312,
            "parentId": 0,
            "menuName": "Services",
            "url": null,
            "icon": "el-icon-s-goods",
            "orderNum": 2,
            "open": 0,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 229,
                "parentId": 312,
                "menuName": "Resources",
                "url": "",
                "icon": "el-icon-date",
                "orderNum": 1,
                "open": 1,
                "disabled": false,
                "perms": "el-icon-date",
                "type": 0,
                "children": [
                  {
                    "id": 230,
                    "parentId": 229,
                    "menuName": "Stock",
                    "url": "/inStocks",
                    "icon": "el-icon-date",
                    "orderNum": 1,
                    "open": 1,
                    "disabled": false,
                    "perms": "el-icon-date",
                    "type": 0,
                    "children": []
                  },
                  {
                    "id": 267,
                    "parentId": 229,
                    "menuName": "Products",
                    "url": "/products",
                    "icon": "el-icon-goods",
                    "orderNum": 2,
                    "open": 0,
                    "disabled": false,
                    "perms": "",
                    "type": 0,
                    "children": []
                  },
                  {
                    "id": 268,
                    "parentId": 229,
                    "menuName": "Categories",
                    "url": "/productCategorys",
                    "icon": "el-icon-star-off",
                    "orderNum": 2,
                    "open": 0,
                    "disabled": false,
                    "perms": "",
                    "type": 0,
                    "children": []
                  },
                  {
                    "id": 270,
                    "parentId": 229,
                    "menuName": "Out stock",
                    "url": "/outStocks",
                    "icon": "el-icon-goods",
                    "orderNum": 5,
                    "open": 1,
                    "disabled": false,
                    "perms": "",
                    "type": 0,
                    "children": []
                  },
                  {
                    "id": 316,
                    "parentId": 229,
                    "menuName": "Production",
                    "url": "/stocks",
                    "icon": "el-icon-tickets",
                    "orderNum": 5,
                    "open": 0,
                    "disabled": false,
                    "perms": "",
                    "type": 0,
                    "children": []
                  }
                ]
              },
              {
                "id": 311,
                "parentId": 312,
                "menuName": "Traffic",
                "url": null,
                "icon": "el-icon-edit",
                "orderNum": 3,
                "open": 0,
                "disabled": false,
                "perms": null,
                "type": 0,
                "children": [
                  {
                    "id": 310,
                    "parentId": 311,
                    "menuName": "Consumers",
                    "url": "/consumers",
                    "icon": "el-icon-edit",
                    "orderNum": 1,
                    "open": 0,
                    "disabled": false,
                    "perms": "",
                    "type": 0,
                    "children": []
                  },
                  {
                    "id": 269,
                    "parentId": 311,
                    "menuName": "Suppliers",
                    "url": "/suppliers",
                    "icon": "el-icon-coordinate",
                    "orderNum": 5,
                    "open": 0,
                    "disabled": false,
                    "perms": "",
                    "type": 0,
                    "children": []
                  }
                ]
              }
            ]
          },
          {
            "id": 303,
            "parentId": 0,
            "menuName": "Health Report",
            "url": "",
            "icon": "el-icon-platform-eleme",
            "orderNum": 3,
            "open": 0,
            "disabled": false,
            "perms": "",
            "type": 0,
            "children": [
              {
                "id": 273,
                "parentId": 303,
                "menuName": "Covid-19",
                "url": "/map",
                "icon": "el-icon-s-opportunity",
                "orderNum": 1,
                "open": 1,
                "disabled": false,
                "perms": "map:view",
                "type": 0,
                "children": []
              },
              {
                "id": 304,
                "parentId": 303,
                "menuName": "Health Check",
                "url": "/health",
                "icon": "el-icon-s-cooperation",
                "orderNum": 1,
                "open": 0,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              },
              {
                "id": 305,
                "parentId": 303,
                "menuName": "Check",
                "url": null,
                "icon": "el-icon-c-scale-to-original",
                "orderNum": 2,
                "open": 1,
                "disabled": false,
                "perms": null,
                "type": 0,
                "children": []
              },
              {
                "id": 272,
                "parentId": 303,
                "menuName": "Scams",
                "url": "/rumors",
                "icon": "el-icon-help",
                "orderNum": 5,
                "open": 0,
                "disabled": false,
                "perms": null,
                "type": 0,
                "children": []
              }
            ]
          },
          {
            "id": 295,
            "parentId": 0,
            "menuName": "Others",
            "url": "",
            "icon": "el-icon-s-marketing",
            "orderNum": 5,
            "open": 0,
            "disabled": false,
            "perms": "",
            "type": 0,
            "children": [
              {
                "id": 297,
                "parentId": 295,
                "menuName": "Survey",
                "url": "",
                "icon": "el-icon-warning",
                "orderNum": 1,
                "open": 0,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": [
                  {
                    "id": 298,
                    "parentId": 297,
                    "menuName": "SQL",
                    "url": "/druid",
                    "icon": "el-icon-view",
                    "orderNum": 1,
                    "open": 0,
                    "disabled": false,
                    "perms": null,
                    "type": 0,
                    "children": []
                  }
                ]
              },
              {
                "id": 341,
                "parentId": 295,
                "menuName": "Demo",
                "url": "/blog",
                "icon": "el-icon-view",
                "orderNum": 1,
                "open": 0,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              },
              {
                "id": 296,
                "parentId": 295,
                "menuName": "swagger",
                "url": "/swagger",
                "icon": "el-icon-document",
                "orderNum": 2,
                "open": 0,
                "disabled": false,
                "perms": null,
                "type": 0,
                "children": []
              },
              {
                "id": 318,
                "parentId": 295,
                "menuName": "Icons",
                "url": "/icons",
                "icon": "el-icon-star-off",
                "orderNum": 2,
                "open": 1,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              }
            ]
          },
          {
            "id": 5,
            "parentId": 0,
            "menuName": "Logs",
            "url": "/logs",
            "icon": "el-icon-camera",
            "orderNum": 6,
            "open": 0,
            "disabled": false,
            "perms": null,
            "type": 0,
            "children": [
              {
                "id": 271,
                "parentId": 5,
                "menuName": "Login Logs",
                "url": "/loginLog",
                "icon": "el-icon-date",
                "orderNum": 1,
                "open": 0,
                "disabled": false,
                "perms": "login:log",
                "type": 0,
                "children": []
              },
              {
                "id": 307,
                "parentId": 5,
                "menuName": "Operations",
                "url": "/logs",
                "icon": "el-icon-edit",
                "orderNum": 1,
                "open": 1,
                "disabled": false,
                "perms": "",
                "type": 0,
                "children": []
              }
            ]
          }
        ]
    }
  },
  methods:
    {
      path(paths) {
        window.sessionStorage.getItem("activePath", paths)
        this.paths = paths;
      }
    }
}
</script>

<style scoped lang="less">
.main-container {
  height: 100%;
}

.el-header {
  background-color: #545c64;
  color: white;
  display: inline-flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  font-size: 20px;

  .el-menu-demo {
    background-color: #001529;
    //span{span:hover{background-color: red;}}
    i {
      color: white;
    }
  }

  .left-box {
    display: flex;
    align-items: center;

    img {
      width: 60px;
      height: 60px;
      margin: 0 0 10px 10px;
    }

    span {
      margin-left: 20px;
    }
  }

  .right-box {
    margin-right: 10px;

    .el-dropdown > img {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      background-color: lightblue;

    }
  }
}


//.el-aside {
//  background-color: #001529;
//  text-align: center;
//  overflow: hidden;
//
//  .el-menu{border-right: none;}
//  .toggle-box
//  {
//    height: 50px;
//    text-align: center;
//    cursor:pointer;
//    background-color: red;
//    overflow: hidden;
//  }
//}

.el-main {
  background-color: #e9eef3;
}

.el-dropdown-link {
  cursor: pointer;
  color: blue;
}


</style>
