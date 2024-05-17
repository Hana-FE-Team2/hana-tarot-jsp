var card_lists = [];

// 서버로부터 데이터를 가져와서 card_lists 배열에 추가
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
