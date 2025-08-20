(function () {
    // 確保 DOM 已加載
    document.addEventListener('DOMContentLoaded', function () {
        // 選擇所有帶有 my-button 類別的按鈕
        const buttons = document.querySelectorAll('.my-button');
        
        // 添加事件監聽器
        buttons.forEach(function (button) {
            button.addEventListener('click', function () {

                // 獲取按鈕名稱
                const buttonName = button.dataset.name;
                alert('按鈕名稱：' + buttonName);
            });
        });
    });

    // 保留原始 console.log 用於調試
    console.log("Hello from esi.practice.site ClientLib!");
})();