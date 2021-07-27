package utp.ts.spoilerroom.dao.interfaces;

import java.util.List;
import utp.ts.spoilerroom.dto.MediaDTO;

public interface MediaDAO extends BaseDAO<MediaDTO>
{
	List<MediaDTO> fetchAllStreamingMedia();
}
