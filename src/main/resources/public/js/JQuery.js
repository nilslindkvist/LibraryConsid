var APIurl = "http://localhost:4040"

/*
function viewListPage() {
    $.ajax({
        url:"listPage.html"
    }).done(function(response){
        console.log("Bytade till listPage ");
        $('body').load("listPage.html");
    });
}
*/

function viewEditItem(id) {
    $.ajax({
    }).done(function(response){
        $('body').load('editItem.html');
        viewItem(id);
    });
}

function viewItem(id) {
    $.getJSON({
        url: APIurl + "/items/" + id
    }).done(function(response){
        const item = response;
        console.log(item);
        populateItemFields(item);
        //$('#clickedRow').text(JSON.stringify(response));
        //$('header').load('editItem.html');
    });
}

function getAllItems() {
    $.getJSON( APIurl + "/items", function( data ) {      
    }).done(function(response){
        console.log(response);
        loadItemTable(response);
    });
}

function loadItemTable(items){
    items.forEach(item => {
        let row = table.insertRow();
        row.id = item.id;
        let itemCategory = row.insertCell(0);
        itemCategory.innerHTML = item.category.categoryName;
        let title = row.insertCell(1);
        let regExTitle = item.title.match(/\b(\w)/gm);
        console.log(regExTitle);
        title.innerHTML = item.title + " (";
        for (i = 0; i < regExTitle.length; i++) {
            title.innerHTML += regExTitle[i].toUpperCase();
        }
        title.innerHTML += ")";
        let author = row.insertCell(2);
        item.author == null ? author.innerHTML = "" : author.innerHTML = item.author;
        let pages = row.insertCell(3);
        item.pages == null ? pages.innerHTML = "" : pages.innerHTML = item.pages;
        let runtime = row.insertCell(4);
        item.runTimeMinutes == null ? runtime.innerHTML = "" : runtime.innerHTML = item.runTimeMinutes;
        let borrower = row.insertCell(5);
        borrower.innerHTML = item.borrower;
        console.log(item.borrower);
        console.log(item.borrowDate);
        let borrowDate = row.insertCell(6);
        borrowDate.innerHTML = item.borrowDate;
        let type = row.insertCell(7);
        type.innerHTML = item.type;
    });
}

function createItem(object) {
    console.log(object);
    $.ajax({
        method: "POST",
        url: APIurl + "/items",
        data: JSON.stringify(object)
    }).done(function(response){
        console.log(response);
    });
}

function updateItem(object) {
    console.log("update item id: " + object["id"]);
    $.ajax({
        method: "PUT",
        url: APIurl + "/items/" + object["id"],
        data: JSON.stringify(object)
    }).done(function(response){
        console.log(response);
    });  
}

function deleteItem(object) {
    console.log("delete item id: " + object["id"]);
    $.ajax({
        method: "DELETE",
        url: APIurl + "/items/" + object["id"],
        data: JSON.stringify(object)
    }).done(function(response){
        console.log(response);
    });
}

function getAllCategories() {
    $.getJSON( APIurl + "/categories", function( data ) {
    }).done(function(response){
        console.log(response);
        loadCategoryTable(response);
    });
}

function loadCategoryTable(categories){
    categories.forEach(category => {
        let row = table.insertRow();
        row.id = category.id;
        let id = row.insertCell(0);
        id.innerHTML = category.id;
        let name = row.insertCell(1);
        name.innerHTML = category.categoryName;
    });
}

function viewEditCategory(id) {
    $.ajax({
    }).done(function(response){
        $('body').load('editCategory.html');
        viewCategory(id);
    });
}

function viewCategory(id) {
    $.getJSON({
        url: APIurl + "/categories/" + id
    }).done(function(response){
        const category = response;
        console.log(response);
        populateCategoryFields(category);
    });
}

function createCategory(object) {
    console.log(object);
    $.ajax({
        method: "POST",
        url: APIurl + "/categories",
        data: JSON.stringify(object)
    }).done(function(response){
        console.log(response);
    });
}

function updateCategory(object) {
    console.log("update category id: " + object["id"]);
    $.ajax({
        method: "PUT",
        url: APIurl + "/categories/" + object["id"],
        data: JSON.stringify(object)
    }).done(function(response){
        console.log(response);
    });  
}

function deleteCategory(object) {
    console.log("delete category id: " + object["id"]);
    $.ajax({
        method: "DELETE",
        url: APIurl + "/categories/" + object["id"],
        data: JSON.stringify(object)
    }).done(function(response){
        console.log(response);
    });

}