<template>
  <div>
    <el-dialog v-bind="$attrs" :visible.sync="showDialog" @open="onOpen" @close="onClose" title="Add New User">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
                 label-position="left">
          <el-col :span="24">
            <el-form-item label="Avatar" required>
              <!--            <el-upload ref="avatar" :file-list="avatarfileList" :action="avatarAction"-->
              <!--                       :before-upload="avatarBeforeUpload" list-type="picture" accept="image/*">-->
              <!--              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>-->
              <!--            </el-upload>-->
              <pan-thumb :image="image"/>
              <el-button type="primary" icon="el-icon-upload" @click="imageShow=true">Change Avatar</el-button>

              <image-cropper
                v-show="imageShow"
                :width="250"
                :height="250"
                :key="imageKey"
                :url="'/ossservice/upload/uploadImgFile'"
                field="file"
                @close="closeImage"
                @crop-upload-success="cropSuccess"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Username" prop="username">
              <el-input v-model="formData.username" placeholder="Prompt your Username" clearable
                        prefix-icon='el-icon-user-solid' :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Email" prop="email">
              <el-input v-model="formData.email" placeholder="Prompt your Email" clearable prefix-icon='el-icon-message'
                        :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Gender" prop="sex">
              <el-radio-group v-model="formData.sex" size="medium">
                <el-radio v-for="(item, index) in sexOptions" :key="index" :label="item.value" :disabled="item.disabled"
                          border>{{ item.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Nickname" prop="nickname">
              <el-input v-model="formData.nickname" placeholder="Prompt your Nickname" clearable
                        prefix-icon='el-icon-user' :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Password" prop="password">
              <el-input v-model="formData.password" placeholder="Prompt your Password" clearable
                        prefix-icon='el-icon-lock' show-password :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Phone" prop="phoneNumber">
              <el-input v-model="formData.phoneNumber" placeholder="Prompt your Phone" clearable
                        :style="{width: '100%'}"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Birthday" prop="birth">
              <el-date-picker v-model="formData.birth" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                              :style="{width: '100%'}" placeholder="Select your Birthday" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Department" prop="departmentId">
              <el-select v-model="formData.departmentId" placeholder="Select your department" clearable
                         :style="{width: '100%'}">
                <el-option v-for="(item, index) in departments" :key="index" :label="item.name" :value="item.value"
                           :disabled="item.disabled"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item size="large">
              <el-button type="primary" @click="handelConfirm">Submit</el-button>
              <el-button @click="close">Reset</el-button>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </el-dialog>
  </div>
</template>
<script>
import ImageCropper from "../../components/ImageCropper"
import PanThumb from "../../components/PanThumb"

export default {
  components: {ImageCropper, PanThumb},
  // 接受父组件传递的值
  props:
    {
      addOrUpdateVisible:
        {
          type: Boolean,
          default: false
        },
      departments:
        {
          type: Array,
          default: []
        }
    },
  data() {
    return {
      formData:
        {
          avatar: null,
          username: undefined,
          email: undefined,
          sex: 1,
          nickname: undefined,
          password: "",
          phoneNumber: undefined,
          birth: null,
          departmentId: undefined,
        },
      // 控制弹出框显示隐藏
      showDialog: false,
      imageShow: false, // 是否显示上传组件
      imagecropperKey: 0, // 上传组件id
      image: 'https://wpimg.wallstcn.com/577965b9-bb9e-4e02-9f0c-095b41417191',

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
        departmentId:
          [{
            required: true, message: 'Select your department', trigger: 'change'
          }],
      },
      avatarAction: 'https://jsonplaceholder.typicode.com/posts/',
      avatarfileList: [],
      sexOptions:
        [{
          "label": "Male",
          "value": 1
        },
          {
            "label": "female",
            "value": 2
          }],
    }
  },
  watch:
    {
      addOrUpdateVisible() {
        this.showDialog = this.addOrUpdateVisible
      },
    },
  methods:
    {
      onOpen() {
        this.showDialog = true
      },
      onClose() {
        this.$refs['elForm'].resetFields()
        this.$emit('changeShow', 'false')
      },
      close() {
        this.$emit('changeShow', 'false')
      },
      handelConfirm() {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          console.log(this.formData)
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
      cropSuccess(data) {
        console.log(data)
        this.imageShow = false
        this.image = data.url;
        // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
        this.imagecropperKey = this.imagecropperKey + 1
      },
      // 关闭上传组件
      closeImage() {
        this.imageShow = false
        // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
        this.imagecropperKey = this.imagecropperKey + 1
      }

    }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
