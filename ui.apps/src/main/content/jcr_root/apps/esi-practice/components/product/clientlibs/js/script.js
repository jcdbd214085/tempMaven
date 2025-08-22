(function () {

    document.addEventListener('DOMContentLoaded', function () {
        // 選擇所有帶有 my-button 類別的按鈕
        const buttons = document.querySelectorAll('.my-button');
        
        // 為每個按鈕添加點擊事件監聽器
        buttons.forEach(function (button) {
            button.addEventListener('click', function () {
                // 獲取按鈕名稱（優先從 data-name 屬性獲取）
                const buttonName = button.dataset.name || button.textContent.trim() || '未命名按鈕';
                alert('按鈕名稱：' + buttonName);
            });
        });
    });
//

        // 從 data-api-path 屬性讀取資源路徑
        const apiPath = document.querySelector('[data-api-path]').dataset.apiPath;
        fetch(apiPath + '.data.json')  // 調用 API: /content/.../product.data.json
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Product Data:', data);  // 在控制台輸出 JSON
                // 可選：動態更新頁面，例如 document.querySelector('.product-title').textContent = data.productTitle;
            })
            .catch(error => {
                console.error('Error fetching product data:', error);
            });
    console.log("Hello from esi.practice.site ClientLib!");
})();