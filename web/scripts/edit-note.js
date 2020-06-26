function editNote(id, title, content) {
    document.documentElement.scrollIntoView(true);
    $("#edit-note").modal("show");
    $("#edit-note-title").val(title);
    $("#edit-note-content").val(content.replace(/<br>/g, "\n"));
    $("#edit-note-form").attr("action", "/edit-note?id=" + id);
}