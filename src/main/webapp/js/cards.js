var card_lists = [];

function fetchTarotCards() {
    if (card_lists.length === 0) {
        fetch('./tarot-cards')
            .then(response => response.json())
            .then(data => {
                card_lists = data.map(card => ({
                    nameEn: card.nameEn,
                    nameKo: card.nameKo,
                    url: card.url,
                    description: card.description
                }));
                console.log(card_lists); // 데이터 확인을 위해 출력
            })
            .catch(error => console.error('Error fetching tarot cards:', error));
    } else {
        console.log('Tarot cards already fetched.');
    }
}

// 함수 호출
fetchTarotCards();
