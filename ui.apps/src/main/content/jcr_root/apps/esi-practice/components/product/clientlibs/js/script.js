(function() {
    "use strict";

    // 監聽文件載入完成事件，確保 DOM 元素已準備好
    $(document).ready(function() {

        // 找到所有具有 .my-button 類別的按鈕
        $('.my-button').on('click', function() {
            // 從按鈕的 data 屬性中讀取名稱
            var buttonName = $(this).data('button-name');

            // 顯示 alert 彈跳視窗
            if (buttonName) {
                alert('按鈕名稱: ' + buttonName);
            }
        });
    });

})();