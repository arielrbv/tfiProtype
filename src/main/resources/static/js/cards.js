var card1 = document.getElementById('card1'),
    card2 = document.getElementById('card2'),
    card3 = document.getElementById('card3'),
    card4 = document.getElementById('card4'),
    cardActive = null,
    enabled = 'false';



//window.onload = disableCards;
function disableCards() {
    card1.classList.add('zinactive');
    card1.classList.remove('zactive', 'zdisabled', 'z-depth-5', 'waves-effect', 'waves-light', 'z-depth-1', 'hoverable');
    card2.classList.add('zinactive');
    card2.classList.remove('zactive', 'zdisabled', 'z-depth-5', 'waves-effect', 'waves-light', 'z-depth-1', 'hoverable');
    card3.classList.remove('zdisabled', 'zactive', 'z-depth-5');
    card3.classList.add('hoverable', 'z-depth-1');
    card4.classList.remove('zdisabled', 'zactive', 'z-depth-5');
    card4.classList.add('hoverable', 'z-depth-1');

}


function initCards() {
    card1.classList.remove('zinactive', 'zdisabled', 'zactive', 'z-depth-5', );
    card1.classList.add('hoverable', 'z-depth-1');
    card2.classList.remove('zinactive', 'zdisabled', 'zactive', 'z-depth-5');
    card2.classList.add('hoverable', 'z-depth-1');
    card3.classList.remove('zdisabled', 'zactive', 'z-depth-5');
    card3.classList.add('hoverable', 'z-depth-1');
    card4.classList.remove('zdisabled', 'zactive', 'z-depth-5');
    card4.classList.add('hoverable', 'z-depth-1');
};

function initCards3and4() {
    card3.classList.remove('zdisabled', 'zactive', 'z-depth-5');
    card3.classList.add('hoverable', 'z-depth-1');
    card4.classList.remove('zdisabled', 'zactive', 'z-depth-5');
    card4.classList.add('hoverable', 'z-depth-1');
};


/*$('.card').on('click', function() {
    console.log('entra');
});*/



card1.addEventListener('click', function() {
    if (enabled == 'true') {
        if (cardActive != 'c1') {
            initCards()
            cardActive = 'c1';
            card1.classList.add('zactive', 'z-depth-5');
            card1.classList.remove('hoverable', 'z-depth-1');
            card2.classList.add('zdisabled');
            card2.classList.remove('hoverable', 'z-depth-1');
            card3.classList.add('zdisabled');
            card3.classList.remove('hoverable', 'z-depth-1');
            card4.classList.add('zdisabled');
            card4.classList.remove('hoverable', 'z-depth-1');
        } else {
            cardActive = null;
            card1.classList.remove('zactive', 'z-depth-5');
            card1.classList.add('hoverable', 'z-depth-1');
            card2.classList.remove('zdisabled');
            card2.classList.add('hoverable', 'z-depth-1');
            card3.classList.remove('zdisabled');
            card3.classList.add('hoverable', 'z-depth-1');
            card4.classList.remove('zdisabled');
            card4.classList.add('hoverable', 'z-depth-1');
        }
    }
});

card2.addEventListener('click', function() {
    if (enabled == 'true') {
        if (cardActive != 'c2') {
            initCards()
            cardActive = 'c2';
            card2.classList.add('zactive', 'z-depth-5');
            card2.classList.remove('hoverable', 'z-depth-1');
            card3.classList.add('zdisabled');
            card3.classList.remove('hoverable', 'z-depth-1');
            card4.classList.add('zdisabled');
            card4.classList.remove('hoverable', 'z-depth-1');
            card1.classList.add('zdisabled');
            card1.classList.remove('hoverable', 'z-depth-1');

        } else {
            cardActive = null;
            card2.classList.remove('zactive', 'z-depth-5');
            card2.classList.add('hoverable', 'z-depth-1');
            card3.classList.remove('zdisabled');
            card3.classList.add('hoverable', 'z-depth-1');
            card4.classList.remove('zdisabled');
            card4.classList.add('hoverable', 'z-depth-1');
            card1.classList.remove('zdisabled');
            card1.classList.add('hoverable', 'z-depth-1');
        }
    }
});

card3.addEventListener('click', function() {
    if (enabled == 'true') {
        if (cardActive != 'c3') {
            initCards()
            cardActive = 'c3';
            card3.classList.add('zactive', 'z-depth-5');
            card3.classList.remove('hoverable', 'z-depth-1');
            card4.classList.add('zdisabled');
            card4.classList.remove('hoverable', 'z-depth-1');
            card1.classList.add('zdisabled');
            card1.classList.remove('hoverable', 'z-depth-1');
            card2.classList.add('zdisabled');
            card2.classList.remove('hoverable', 'z-depth-1');

        } else {
            cardActive = null;
            card3.classList.remove('zactive', 'z-depth-5');
            card3.classList.add('hoverable', 'z-depth-1');
            card4.classList.remove('zdisabled');
            card4.classList.add('hoverable', 'z-depth-1');
            card1.classList.remove('zdisabled');
            card1.classList.add('hoverable', 'z-depth-1');
            card2.classList.remove('zdisabled');
            card2.classList.add('hoverable', 'z-depth-1');
        }
    } else {
        if (cardActive != 'c3') {
            initCards3and4()
            cardActive = 'c3';
            card3.classList.add('zactive', 'z-depth-5');
            card3.classList.remove('hoverable', 'z-depth-1');
            card4.classList.add('zdisabled');
            card4.classList.remove('hoverable', 'z-depth-1');

        } else {
            cardActive = null;
            card3.classList.remove('zactive', 'z-depth-5');
            card3.classList.add('hoverable', 'z-depth-1');
            card4.classList.remove('zdisabled');
            card4.classList.add('hoverable', 'z-depth-1');
        }
    }
});

card4.addEventListener('click', function() {
    if (enabled == 'true') {
        if (cardActive != 'c4') {
            initCards()
            cardActive = 'c4';
            card4.classList.add('zactive', 'z-depth-5');
            card4.classList.remove('hoverable', 'z-depth-1');
            card1.classList.add('zdisabled');
            card1.classList.remove('hoverable', 'z-depth-1');
            card2.classList.add('zdisabled');
            card2.classList.remove('hoverable', 'z-depth-1');
            card3.classList.add('zdisabled');
            card3.classList.remove('hoverable', 'z-depth-1');

        } else {
            cardActive = null;
            card4.classList.remove('zactive', 'z-depth-5');
            card4.classList.add('hoverable', 'z-depth-1');
            card1.classList.remove('zdisabled');
            card1.classList.add('hoverable', 'z-depth-1');
            card2.classList.remove('zdisabled');
            card2.classList.add('hoverable', 'z-depth-1');
            card3.classList.remove('zdisabled');
            card3.classList.add('hoverable', 'z-depth-1');
        }
    } else {
        if (cardActive != 'c4') {
            initCards3and4();
            cardActive = 'c4';
            card4.classList.add('zactive', 'z-depth-5');
            card4.classList.remove('hoverable', 'z-depth-1');
            card3.classList.add('zdisabled');
            card3.classList.remove('hoverable', 'z-depth-1');

        } else {
            cardActive = null;
            card4.classList.remove('zactive', 'z-depth-5');
            card4.classList.add('hoverable', 'z-depth-1');
            card3.classList.remove('zdisabled');
            card3.classList.add('hoverable', 'z-depth-1');
        }
    }
});

$('.switch').on('change', function() {
    if (enabled == 'false') {
        initCards();
        enabled = 'true';
    } else {
        disableCards();
        enabled = 'false';
    }
});