function hideDialogOnSuccess(args, dialogWidgetVar) {
    if (args && !args.validationFailed) {
        dialogWidgetVar.hide();
    }
}


$(function() {
    $(document).tooltip({
        position: {
            my: "center bottom",
            at: "center top"
        }
    }).off("focusin focusout");
});

