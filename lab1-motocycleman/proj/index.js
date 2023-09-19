const model = [
    {type: 'title', value:'Hello world!'},
    {type:'text', value:'Still text'},
    {type:'columns',value:[
         'Get',
         'out'
         ]},
    {type:'image',value:'./assets/test.png'}
]

const $site = document.querySelector('#site')
model.forEach(block => {
let html =''
    if (block.type === 'title'){
    html = title(block)
    }else if (block.type === 'text'){
    html = text(block)
     }else if (block.type === 'columns'){
     html = columns(block)
     }else if (block.type === 'image'){
     html= image(block)
     }


     $site.insertAdjacentHTML('beforeend',html)
})

function title(block){
return`
    <div class="row">
        <div class="col-sm">
          <h1>${block.value}</h1>
        </div>
    </div>
`
}

function text(block){
return`
    <div class="row">
        <div class="col-sm">
            <h1>${block.value}</h1>
        </div>
    </div>
`
}

function columns(block){
const html = block.value.map(item => `<div class="col-sm">${item} </div>`)
return `
    <div class="row">
    ${html.join('')}
`
}

function image(block){
return`
    <div class="row">
       <img src="${block.value}" />
    </div>
`}



