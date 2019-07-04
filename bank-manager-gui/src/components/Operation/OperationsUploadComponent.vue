<template>

    <div class="operations component upload">

        <div class="large-12 medium-12 small-12 cell">
            <label>File
                <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
            </label>
        <button v-on:click="submitFile()">Submit</button>
    </div>

    </div>

</template>

<script>

import axios from 'axios'

export default {
    
    name: 'OperationsUploadComponents',

    components:{},
    data(){
        return {
            file: ''
        }
    },
    methods: {
        handleFileUpload(){
            this.file = this.$refs.file.files[0];
        },
         submitFile: function(){
            console.log('submit file');
            var _self = this;
            let formData = new FormData();
            formData.append('file', this.file);

            axios.post( 'http://' + process.env.VUE_APP_API_URL + '/operations/upload',
                    formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }
                    ).then(function(){
                        console.log('SUCCESS!!');
                        _self.$emit('operationschange');
                    })
                    .catch(function(){
                    console.log('FAILURE!!');
                    });
        }
    },

}
</script>

<style scoped>

    .component.upload {
        width: 400px;
    }

</style>