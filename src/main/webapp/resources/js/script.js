
function hideDialogOnSuccess(args, dialogWidgetVar) {
    if (args && !args.validationFailed) {
        dialogWidgetVar.hide();
    }
}

