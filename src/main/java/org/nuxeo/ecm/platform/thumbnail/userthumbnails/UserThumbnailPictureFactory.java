package org.nuxeo.ecm.platform.thumbnail.userthumbnails;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.picture.thumbnail.ThumbnailPictureFactory;

public class UserThumbnailPictureFactory extends ThumbnailPictureFactory {

    private static final Log log = LogFactory.getLog(UserThumbnailPictureFactory.class);

    @Override
    public Blob getThumbnail(DocumentModel doc, CoreSession session)
            throws ClientException {


        //Check for UserThumb Facet and content

        Blob thumbnailBlob = null;
        try {
            if (doc.hasSchema("userthumbnail"))  {
                thumbnailBlob = (Blob) doc.getPropertyValue("userthumbnail:thumbnail");
            }
        } catch (ClientException e) {
            log.warn("Could not fetch the thumbnail blob", e);
        }
        if (thumbnailBlob == null) {
            return super.getThumbnail(doc, session);

        }
        return thumbnailBlob;
    }

}
