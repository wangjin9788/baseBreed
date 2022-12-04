<template>
  <quill-editor
    v-model="content"
    ref="myQuillEditor"
    :options="editorOption"
    @blur="onEditorBlur($event)"
    @focus="onEditorFocus($event)"
    @change="onEditorChange($event)">
  </quill-editor>
</template>

<script>
export default {
  data() {
    return {
      content: `<p>这是 vue-quill-editor 的内容！</p>`, //双向数据绑定数据
      editorOption: {}, //编辑器配置项
    }
  },
  methods: {
    onEditorBlur() {}, // 失去焦点触发事件
    onEditorFocus() {}, // 获得焦点触发事件
    onEditorChange() {}, // 内容改变触发事件
  }
}
</script>
