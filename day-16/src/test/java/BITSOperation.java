import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BITSOperation extends BITSPackage {
	protected String iLabel;
	protected int totalLength;
	protected int numbSubpackages;
	protected List<BITSPackage> subpackages = new ArrayList<BITSPackage>();

	public BITSOperation(String versionAndTypeAndILabel) {
		super(versionAndTypeAndILabel.substring(0, 6));
	}
	
	@Override
	void add(String bin) {
		super.add(bin);
	}

	@Override
	int nextToRead() {
		if(iLabel == null) {
			return 1;
		}
		if("0".equals(iLabel)) {
			if(totalLength == 0) {
				return 15;
			}
			return totalLength;
		}
		if("1".equals(iLabel)) {
			if(numbSubpackages == 0) {
				return 11;
			}
			if(subpackages.size() < numbSubpackages) {
				// need to read subpackages
				return subpackages.get(subpackages.size()-1).nextToRead();
			}
		}
		return 0;
	}
}