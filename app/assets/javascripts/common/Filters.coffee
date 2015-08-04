@filtersModule
.filter 'isPlainText', [
    () ->
        (contentTypeId, contentTypes) ->
            for ct in contentTypes
                if ct.id is contentTypeId and ct.contentType is 'text/plain'
                    return yes

            no
]

