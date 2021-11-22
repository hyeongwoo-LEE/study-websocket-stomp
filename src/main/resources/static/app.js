var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stompTest');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
    });
}

function  subscribe(){
    stompClient.subscribe('/topic/chatrooms', function (chatRoomResDTO) {
        showGreeting(JSON.parse(chatRoomResDTO.body));
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/pub/chatrooms", {}, JSON.stringify({'masterId': 1, 'title': $("#name").val()}));
}

function showGreeting(chatRoomResDTO) {
    console.log(chatRoomResDTO);
    $("#greetings").append(`<tr><td> ${chatRoomResDTO.title} </td> <td>${chatRoomResDTO.participantCount}</td></tr>`);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

