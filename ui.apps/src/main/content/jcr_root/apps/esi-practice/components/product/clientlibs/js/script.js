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
    console.log("Hello from esi.practice.site ClientLib!");
})();