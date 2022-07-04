var codesApi = Vue.resource('/codes{/id}');

Vue.component('message-form', {
    props: ['messages'],
    data: function() {
        return {
            messages: '',
            message: '',
            phone: ''
        }
    },

    template:
        '<div>' +
        '<h2>Enter your telephone number (11 - 13 digits): </h2>'+
        '<span className="enterCountryDiv">'+
        '+'+
        '<input id="enterCountryNo" aria-label="Enter phone code"  name="enterCountryNo" pattern="[0-9]{11,13}" placeholder="Enter phone code" v-model=phone type ="tel" style="width: 175px; font-size: large; text-align: right" />'+
        '<span className="validity"></span>'+
        '</span>'+
        '<br>'+'<br>'+
        '<input type="button" value="Detect country" @click="detect" />'+
        '<br>'+'<br>'+
        '<input type="button" value="Exit" @click="exit" />'+'<br>'+'<br>'+'<br>'+'<br>'+
        '</div>'+
        '<div v-for="message in messages" {{ message}} </div>',

    methods: {
        detect: function() {
            let phone = this.phone;

                codesApi.save({},phone).then(result =>
                    result.json().then(data => {
                        console.log(data)
                        this.messages.push(data);
                        this.phone = ''
                    })
                )
            },
        exit:function () {
            window.location.href = "http://localhost:8080/exit"
            window.close();
        }
    }
});

Vue.component('message-row', {
    props: ['message'],
    template: '<div>  {{ message}} </div>'

});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null,
            messages: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<message-form :messages="messages" :messageAttr="message" />' +
        'Список стран:'+
        '<div> <message-row v-for="message in messages" :message="message" /></div> '+
        '</div>',

    created: function() {
     codesApi.get().then(result =>
         result.json().then(data=>
         console.log(data))
        )
    },
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
