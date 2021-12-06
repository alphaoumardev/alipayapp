<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px; padding-bottom: 10px; font-size: 15px;">
      <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/">Users</a></el-breadcrumb-item>
    </el-breadcrumb>
    <!--  The card-->
    <el-card class="box-card">
      <el-form :inline="true" :model="user" class="demo-form-inline">
        <el-form-item label="Department" style="margin-left: 30px;">
          <el-select v-model="user.departmentId" clearable placeholder="Select">
            <el-option
              v-for="item in departments"
              :key="item.id"
              :label="item.name"
              :value="item.id">
              <span style="float: left;">{{ item.name }}</span>
              <span style="float: right; color: lightskyblue; font-size: 15px;"><span
                class="el-tag el-tag--success el-tag--mini el-tag--light">{{ item.deptCount }}</span></span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Username" label-width="80px">
          <el-input clearable v-model="user.username" placeholder="username"/>
        </el-form-item>
        <el-form-item label="Email" label-width="73px">
          <el-input clearable v-model="user.email" placeholder="email"/>
        </el-form-item>
        <el-form-item label="Nickname" label-width="80px">
          <el-input clearable v-model="user.nickname" placeholder="nickname"/>
        </el-form-item>
        <el-form-item label="Radio" label-width="80px">
          <el-radio v-model="user.sex" label="1">Male</el-radio>
          <el-radio v-model="user.sex" label="2">Femal</el-radio>
          <el-radio v-model="user.sex" label="3">Secret</el-radio>
        </el-form-item>
        <el-form-item label-width="100px" style="margin-right:  90px; float: right;">
          <el-button icon="el-icon-refresh" type="info" @click="resetUser">Reset</el-button>
          <el-button icon="el-icon-search" type="primary" @click="getUserList">Query</el-button>
          <el-button icon="el-icon-plus" type="success" @click="centerDialogVisible = true">Adds</el-button>
        </el-form-item>
      </el-form>

      <!--    The table-->
      <el-table
        :data="userList"
        style="width: 100%" border
        max-height="400"
      >
        <el-table-column align="center" prop="id" label="#" width="80"/>
        <el-table-column align="center" prop="username" label="Username"/>
        <el-table-column align="center" prop="sex" label="Gender">
          <template v-slot="scope">
          <span class="el-tag el-tag--success el-tag--mini el-tag--light">
            {{ scope.row.sex === 0 ? '帅哥' : (scope.row.sex === 1 ? '美女' : '保密') }}
          </span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="name" label="Department" sortable/>
        <el-table-column align="center" prop="nickname" label="nickname"/>
        <el-table-column align="center" prop="birth" label="Birthday" sortable/>
        <el-table-column align="center" prop="email" label="Email" width="200"/>
        <el-table-column align="center" prop="phoneNumber" label="Phone"/>
        <el-table-column align="center" label="Status">
          <template v-slot="scope">
            <el-switch
              v-model="status"
              inline-prompt
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="是"
              inactive-text="否"
            />
          </template>
        </el-table-column>
        <el-table-column align="center" prop="" label="Operations" width="170">
          <el-button type="primary" icon="el-icon-edit" circle></el-button>
          <el-button type="danger" icon="el-icon-delete" circle></el-button>
          <el-button type="warning" icon="el-icon-setting" circle></el-button>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:currentPage="page" style="text-align: center;"
        :page-sizes="[7, 15, 25, 40]"
        :page-size="7"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </el-card>
    <!--  The dialog to add the new user-->
    <el-dialog v-model="centerDialogVisible" title="Add New User" @close="onClose">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="100px" label-position="left">
          <el-col :span="24">
            <el-form-item label="Avatar" required>
              <img v-if="image" :src="image" alt="avatar" class="avatar"/>
              <i v-else class="el-icon-plus avatar-uploader-icon"/>
              <el-upload
                ref="avatar"
                v-model="image"
                :action="avatarAction"
                :before-upload="avatarBeforeUpload"
                list-type="picture" accept="image/*"
                :auto-upload="true"
                :show-file-list="false"
                :on-success="handleSuccess"
                @close="closeImage"
                v-show="imageShow"
                :data="imagekey"
                :on-change="handleChange"
              >
                <el-button size="small" type="primary" icon="el-icon-upload">Change</el-button>
              </el-upload>
            </el-form-item>
          </el-col>
          <br>
          <el-col :span="12">
            <el-form-item label="Username" prop="username">
              <el-input v-model="user.username" placeholder="Your Username" clearable prefix-icon='el-icon-user-solid'
                        :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Password" prop="password">
              <el-input v-model="user.password" placeholder="Your Password" clearable prefix-icon='el-icon-lock'
                        show-password :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="Gander " prop="sex">
              <el-radio-group v-model="user.sex" size="medium">
                <el-radio v-for="(item, index) in sexOptions" :key="index" :label="item.value"
                          :disabled="item.disabled">{{ item.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Department" prop="departmentId">
              <el-select v-model="user.departmentId" placeholder="Your department" clearable :style="{width: '100%'}">
                <el-option v-for="(item, index) in departments" :key="index" :label="item.name" :value="item.value"
                           :disabled="item.disabled"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Nickname" prop="nickname">
              <el-input v-model="user.nickname" placeholder="Your Nickname" clearable prefix-icon='el-icon-user'
                        :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Phone" prop="phoneNumber">
              <el-input v-model="user.phoneNumber" placeholder="Your Phone" clearable :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Email" prop="email">
              <el-input v-model="user.email" placeholder="Your Email" clearable prefix-icon='el-icon-message'
                        :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Birthday" prop="birth">
              <el-date-picker v-model="user.birth" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                              :style="{width: '100%'}" placeholder="Your Birthday" clearable/>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="onClose">Cancel</el-button>
        <el-button type="primary" @click="addUser">Confirm</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {addUser, deleteAvatar, lists} from "../../api/users";
import {findandcount} from "../../api/department";

export default {
  name: "Users",

  data() {
    return {
      page: 1,
      total: 0,
      userList: [],
      status: true,
      departments: [],
      imagekey: 0,
      imageShow: true,
      centerDialogVisible: false,
      avatarAction: 'http://localhost:8000/files/upload',
      // headerObject: {Authorization: window.sessionStorage.getItem('token')},
      //THIS IS TO ADD THE NEW USER DIALOG
      user:
        {
          avatar: null,
          username: undefined,
          email: undefined,
          sex: '1',
          nickname: undefined,
          password: "",
          phoneNumber: undefined,
          birth: null,
          departmentId: undefined,
        },
      image: "https://diallo.oss-cn-shanghai.aliyuncs.com/2021-29-05/fcd1ef7f41454116805132311774362f.jpg?versionId=CAEQLBiBgIDRyJO75xciIDRiMjY2YWYxYzBmZTRiYjE5YjFjOWJhMGJmNGRiZDc0",

      rules: {
        username:
          [{
            required: true, message: 'Prompt your Username', trigger: 'blur'
          }],
        email:
          [{
            required: true, message: 'Prompt your Email', trigger: 'blur'
          }],
        sex:
          [{
            required: true, message: 'Gender cant be empty', trigger: 'change'
          }],
        nickname:
          [{
            required: true, message: 'Prompt your Nickname', trigger: 'blur'
          }],
        password:
          [{
            required: true, message: 'Prompt your Password', trigger: 'blur'
          }],
        phoneNumber:
          [{
            required: true, message: 'Prompt your Phone', trigger: 'blur'
          }],
        birth:
          [{
            required: true, message: 'Select your Birthday', trigger: 'change'
          }],
      },
      sexOptions: [{
        "label": "Male",
        "value": 1
      }, {
        "label": "Female",
        "value": 2
      }, {
        "label": "Scret",
        "value": 3
      }],


    }
  },
  created() {
    this.getUserList()
    this.getDepartment()
    this.addUser()
  },
  methods:
    {
      handleSizeChange(val) {
        this.size = val;
        this.getUserList();
      },
      handleCurrentChange(val) {
        this.page = val;
        this.getUserList();
      },
      resetUser() {
        this.user.username = ''
        this.user.departmentId = ''
        this.user.nickname = ''
        this.user.sex = '1'
        this.user.email = ''
      },
      async getUserList() {
        await lists(this.page, this.size, this.user).then(res => {
          this.userList = res.data.data.pageInfo.records
          this.total = res.data.data.pageInfo.total
          // this.forbidden=res.data.data.pageInfo.records.id.forbidden
        })
        // console.log(this.userList)
        // console.log(this.forbidden)

      },
      async getDepartment() {
        await findandcount().then(res => {
          this.departments = res.data.data.depart
        })
        // console.log(this.departments)
      },
      async deleteOldAvatar(oldImage) {
        await deleteAvatar(oldImage)
      },
      // THIS IS TO ADD THE NEW USER DIALOG
      handelConfirm() {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          this.centerDialogVisibles = false
          console.log(this.user)
        })
      },
      avatarBeforeUpload(file) {
        let isRightSize = file.size / 1024 / 1024 < 2
        if (!isRightSize) {
          this.$message.error('The photo is more than 2MB please change it and try again')
        }
        let isAccept = new RegExp('image/*').test(file.type)
        if (!isAccept) {
          this.$message.error('You have to choose an image ')
        }
        return isRightSize && isAccept
      },
      // 上传成功后的回调函数
      onClose() {
        this.$refs['elForm'].resetFields()
        this.centerDialogVisible = false;
      },
      handleSuccess(res, file) {
        if (res.code === 200) {
          const oldAvatar = this.image
          this.user.avatar = this.image = res.data.url;
          this.image = URL.createObjectURL(file.raw)

          this.imagekey = this.imagekey + 1
          this.deleteAvatar(oldAvatar)
        } else {
          this.$message.error(res.message)
        }
      },
      closeImage() {
        this.imageShow = true
        this.imagekey = this.imagekey + 1
        this.image = this.user.avatar
        this.user.avatar = res.data.url
      },
      async addUser() {
        const {data: res} = await addUser(this.user)
        if (res.code === 200) {
          console.log(res.code)
          this.$message.success("You have successfully added a new user")
          this.user = {}
          this.$emit('getUserList')
          this.$emit('getDepartment')
        } else {
          // this.$message.error("Adding new user has failed"+res.message)
        }
      }

    }
}
</script>

<style scoped lang="less">
.el-table .warning-row {
  --el-table-tr-background-color: var(--el-color-warning-lighter);
}

.el-table .success-row {
  --el-table-tr-background-color: var(--el-color-success-lighter);
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 100%;
}
</style>
