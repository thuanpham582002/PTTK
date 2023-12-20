function addToCart(productId) {
    let quantity = document.getElementById("quantity");
    quantity = quantity == null ? 1 : quantity.value;
    console.log("quantity: " + quantity);

    let url = `http://localhost:8080/cart/add`;
    let options = {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            productId,
            quantity,
        }),
    };
    fetch(url, options)
        .then((response) => {
            console.log(response);
            if (response.status == 401) {
                window.location.href = "/login-page";
            } else if (response.status == 200) {
                return response.json();
            }
        })
        .then((data) => {
            if (data) {
                console.log(data);
            }
        });
}

function deleteCartItem(cartItemId) {
    let confirmation = confirm("Are you sure you want to delete?");
    if (confirmation) {
        let row = event.target.parentNode.parentNode
        console.log(row);
        let parent = row.parentNode
        parent.removeChild(row);

        let url = `http://localhost:8080/cart/${cartItemId}`
        let options = {
            method: "DELETE",
            mode: "cors"
        }
        fetch(url, options);
    }
}


function updateCart(cartItemId) {
    let cartItem = document.getElementById(`cart-item-${cartItemId}`);
    let inputs = cartItem.getElementsByTagName("input");
    let quantity = inputs[0].value;

    let url = `http://localhost:8080/cart/update`;
    let options = {
        method: "PUT",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            cartItemId,
            quantity,
        }),
    };
    fetch(url, options).then((response) => console.log(response));
}

function placeOrder() {
    let receiverName = document.getElementById("receiverName").value;
    let receiverMobile = document.getElementById("receiverMobile").value;
    let receiverAddress = document.getElementById("receiverAddress").value;
    let paymentType = document.querySelector(
        'input[name="paymentType"]:checked'
    ).value;
    let createId = document.getElementById("creditId").value;
    if (receiverName == "") {
        alert("Vui lòng điền đầy đủ họ tên");
        return;
    } else if (receiverMobile == "") {
        alert("Vui lòng nhập số điện thoại người nhận");
        return;
    } else if (receiverAddress == "") {
        alert("Vui lòng nhập địa chỉ nhận hàng");
        return;
    }

    if (paymentType == "1") {
        if (createId == "") {
            alert("Vui lòng nhập mã số thẻ");
            return;
        }
    } else {
        createId = "";
    }

    let data = {
        receiverName,
        receiverMobile,
        receiverAddress,
        paymentType,
        createId,
    };

    console.log(data);

    let url = `http://localhost:8080/checkout`;
    let options = {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    };

    fetch(url, options).then((response) => {
        console.log(response);
        if (response.status == 200) {
            alert("Đặt hàng thành công, xin mời tiếp tục mua sắm");
            window.location.href = `http://localhost:8080/shop/all`;
        }
    });
}

function orderDetail(orderId) {
    let url = `http://localhost:8080/checkout/${orderId}`;
    let options = {
        method: "GET",
        mode: "cors",
    };
    fetch(url, options)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);

            content = `<div class="row">

                    <div class="col-lg-4">
                        <div class="card border-secondary mb-5">
                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi-bold m-0">Order Summary</h4>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Name</h6>
                                    <h6 class="font-weight-medium">${data.receiverName}</h6>
                                </div>
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Mobile</h6>
                                    <h6 class="font-weight-medium">${data.receiverMobile}</h6>
                                </div>
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Address</h6>
                                    <h6 class="font-weight-medium">${data.receiverAddress}</h6>
                                </div>
                                <div class="d-flex justify-content-between mb-3 pt-1">
                                    <h6 class="font-weight-medium">Total</h6>
                                    <h6 class="font-weight-medium">$${data.totalPrice}</h6>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h6 class="font-weight-medium">Shipping</h6>
                                    <h6 class="font-weight-medium">$10</h6>
                                </div>
                            </div>
                        </div>
                    </div>

                    <table class="table table-bordered text-center mb-0 col-lg-8">
                        <thead class="bg-secondary text-dark">
                            <tr>
                                <th>Products</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody class="align-middle">`;

            data.orderItemDtoList.forEach(item => {
                content += `<tr>
                        <td class="align-middle"><img src="${item.product.url}" alt="" style="width: 50px;"> ${item.product.name}</td>
                        <td class="align-middle">$${item.product.price}</td>
                        <td class="align-middle">
                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                <input type="text" class="form-control form-control-sm bg-secondary text-center" value="${item.quantity}">
                            </div>
                        </td>
                        <td class="align-middle">$${item.quantity * item.product.price}</td>
                    </tr>`
            })


            content += `</tbody>
                    </table>
                </div>`
            document.getElementById('main-content').innerHTML = content;
        });
}

function filterItems() {
    // Determine the appropriate value based on the selected radio button
    let sortType;
    if (document.getElementById("customRadio1").checked) {
        sortType = 0; // None
    } else if (document.getElementById("customRadio2").checked) {
        sortType = 1; // Ascending
    } else if (document.getElementById("customRadio3").checked) {
        sortType = 2; // Descending
    }

    // Create a URL object with the current URL
    let currentUrl = new URL(window.location.href);

    // Use the URLSearchParams object to manipulate the query parameters
    let searchParams = currentUrl.searchParams;

    // Set the 'filter' parameter to the new value
    searchParams.set('filter', sortType);

    // Update the URL's search parameters
    currentUrl.search = searchParams.toString();

    // Redirect to the new URL
    window.location.href = currentUrl.toString();
}