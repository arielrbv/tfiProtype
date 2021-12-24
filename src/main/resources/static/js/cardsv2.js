var allCards = document.querySelectorAll('.card'),
    activeCards = document.querySelectorAll('.cards:not(.zinactive)'),
    inactiveCards = document.querySelectorAll('.zinactive'),
    previousCardId = null,
    isAllCardsEnabled = 'false';

function deselectThis(allCards) {
    allCards.classList.remove('zdisabled', 'zactive')
}

function selectOther(allCards) {
    allCards.classList.add('zdisabled')
}

function selectThis() {
    if (isAllCardsEnabled == 'false') {
        if (!document.getElementById(this.id).classList.contains('zinactive')) {
            console.log('caso 1.2 - elemento: ' + this.id + ' id previo: ' + previousCardId)
            if (this.id == previousCardId) {
                allCards.forEach(deselectThis)
                previousCardId = null
            } else {
                allCards.forEach(selectOther)
                this.classList.remove('zdisabled')
                this.classList.add('zactive')
                previousCardId = this.id
            }
        }
        /*  else {
                    console.log('caso 1 - elemento: ' + this.id + ' id previo: ' + previousCardId)

                } */
    } else {
        console.log('caso 2 - elemento: ' + this.id + ' id previo: ' + previousCardId)
        if (this.id == previousCardId) {
            allCards.forEach(deselectThis)
            previousCardId = null
        } else {
            allCards.forEach(selectOther)
            this.classList.remove('zdisabled')
            this.classList.add('zactive')
            previousCardId = this.id
        }
    }
}

allCards.forEach(function(allCards) {
    allCards.addEventListener('click', selectThis)
})

function resetInactiveCardsOnView(inactiveCards) {
    if (isAllCardsEnabled == 'false') {
        inactiveCards.classList.add('hoverable', 'z-depth-1')
        inactiveCards.classList.remove('zinactive', 'zdisabled', 'zactive', 'z-depth-5')
    } else {
        inactiveCards.classList.remove('zactive', 'zdisabled', 'z-depth-5', 'waves-effect', 'waves-light', 'z-depth-1', 'hoverable')
        inactiveCards.classList.add('zinactive')
    }
}

$('.switch').on('change', function() {
    if (isAllCardsEnabled == 'false') {
        inactiveCards.forEach(resetInactiveCardsOnView)
        allCards.forEach(deselectThis)
        previousCardId = null
        isAllCardsEnabled = 'true'
    } else {
        inactiveCards.forEach(resetInactiveCardsOnView)
        allCards.forEach(deselectThis)
        previousCardId = null
        isAllCardsEnabled = 'false'
    }
})