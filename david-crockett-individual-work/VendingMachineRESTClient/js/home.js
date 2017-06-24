$(document).ready(function(){

    loadItems();


});

var total = parseFloat('0.00');
var money;
var itemId;
var quarters = 0;
var dimes = 0;
var nickels = 0;
var pennies = 0;
var id;

function loadItems() {
    var itemBox = $('#grid');

    $.ajax({
        type:'GET',
        url:'http://localhost:8080/items',
        success: function(itemArray) {
          $.each(itemArray, function(index, item){
              id = item.id;
              var name = item.name;
              var price = item.price;
              var quantity = item.quantity;

              var currentItem = '<div class="gridItems" style="border-style: solid;" id="itemNum' + id + '">';
                  currentItem += '<div class="form-group">'
                  currentItem += '<button type="button" class="btn btn-primary itemChoice" value="' + id + '">' + id + '</button>';
                  currentItem += '<p style="text-align: center;">' + name + '</p>';
                  currentItem += '<br/>';
                  currentItem += '<p style="text-align: center;" id="itemPrice">$' + price + '</p>';
                  currentItem += '<br/>';
                  currentItem += '<br/>';
                  currentItem += '<p style="text-align: center;" id="itemQuantity"> Quantity left: ' + quantity + '</p>';
                  currentItem += '</div>';
                  currentItem += '</div>'

              itemBox.append(currentItem);
          });
          $('.itemChoice').click(function(event) {
              $('#enterItem').val($(this).val());
          });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        }
    });
};

$('#make-purchase-button').click(function(event) {
    if ($('#enterItem').val() == "") {
      return false;
    }
    itemId = $('#enterItem').val();
    itemId = parseInt(itemId);
    getItem(itemId);
});

function getItem(itemId) {
      $('#errorMessages').empty();

      $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + total + '/item/' + itemId,
        success: function(data, status) {
          $('#enterMessage').val('Thank You!!!');
          $('.gridItems').remove();
          quarters = data.quarters;
          dimes = data.dimes;
          nickels = data.nickels;
          pennies = data.pennies;
          loadItems();
          getChange(quarters, dimes, nickels, pennies);
        },
        error: function(xhr, status, error) {
            jsonMessage = JSON.parse(xhr.responseText);
            $('#enterMessage').val(jsonMessage.message);
        }
      });
};

$('#get-change-button').click(function(event) {
    quarters = 0;
    dimes = 0;
    nickels = 0;
    pennies = 0;
    var userMoney = parseFloat($('#enterMoney').val());
    var floatQuarter = parseFloat('0.25');
    var floatDime = parseFloat('0.10');
    var floatNickel = parseFloat('0.05');
    var floatPenny = parseFloat('0.01');
    while(userMoney >= 0.25) {
        userMoney = userMoney - floatQuarter;
        quarters++;
    }while(userMoney >= 0.10) {
        userMoney = userMoney - floatDime;
        dimes++;
    }while(userMoney >= 0.05) {
        userMoney = userMoney - floatNickel;
        nickels++;
    }while(userMoney >= 0.01) {
        userMoney = userMoney - floatPenny;
        pennies++;
    }
    getChange(quarters, dimes, nickels, pennies);
});

function getChange(quarters, dimes, nickels, pennies) {
    $('#enterMoney').val('0.00');
    total = parseFloat('0.00');
    money = parseFloat('0.00');
    $('#enterChange').val('Q: ' + quarters + ' D: ' + dimes + ' N: ' + nickels + ' P: ' + pennies);
};

function inputMoney(money) {
    total = total + money;
    $('#enterMoney').val(total.toFixed(2)).css('text-align', 'center');
};
$('#add-dollar-button').click(function(event) {
      money = parseFloat('1.00');
      inputMoney(money);
});
$('#add-quarter-button').click(function(event) {
      money = parseFloat('0.25');
      inputMoney(money);
});
$('#add-dime-button').click(function(event) {
      money = parseFloat('0.10');
      inputMoney(money);
});
$('#add-nickel-button').click(function(event) {
      money = parseFloat('0.05');
      inputMoney(money);
});
