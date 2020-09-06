import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
	private Map<String, AutoDebitInfo> infos = new HashMap<>();

	@Override
	public void save(final AutoDebitInfo info) {
		infos.put(info.getUserId(), info);
	}

	@Override
	public AutoDebitInfo findOne(final String userId) {
		return infos.get(userId);
	}
}
