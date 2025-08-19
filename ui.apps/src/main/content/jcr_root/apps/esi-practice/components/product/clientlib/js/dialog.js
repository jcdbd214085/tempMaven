(function($, Coral) {
    "use strict";

    $(document).on("dialog-ready", function() {
        // 1. 監聽「對話框準備就緒」事件
        //    這段程式碼會在每一個對話框被開啟時獨立執行一次。
        //    這確保了每個元件的邏輯都是獨立的，互不影響。

        // 2. 找到當前對話框中的下拉式選單
        var colorSelect = $(this).find(".color-select");
        var dialogContainer = $(this); // 儲存當前對話框的 DOM 元素

        // 3. 監聽下拉式選單的「改變」事件
        colorSelect.on("change", function(event) {
            var selectedValue = $(this).val();
            // 4. 找到對應的標題元素
            //    利用 dialogContainer 變數將搜尋範圍限定在當前對話框內。
            var titleLabel = dialogContainer.find("coral-panel-content .coral-Form-fieldlabel:first");

            // 5. 根據選中的值來改變標題顏色
            //    a. 移除所有舊的顏色類別，避免樣式衝突。
            titleLabel.removeClass("red-text green-text blue-text");

            //    b. 根據選中的值添加對應的顏色類別。
            if (selectedValue === "Color_Red") {
                titleLabel.addClass("red-text");
            } else if (selectedValue === "Color_Green") {
                titleLabel.addClass("green-text");
            } else if (selectedValue === "Color_Blue") {
                titleLabel.addClass("blue-text");
            }
        });
    });

})(jQuery, Coral);