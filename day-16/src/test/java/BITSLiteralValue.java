import java.math.BigInteger;

class BITSLiteralValue extends BITSPackage {
	private String data = "";

	public BITSLiteralValue(String versionAndType) {
		super(versionAndType);
	}
	
	int getValue() {
		return Integer.parseInt(data, 2);
	}

	@Override
	int nextToRead() {
		if(binary.length() < 5) {
			return 5;
		}
		return binary.substring(binary.length()-5).startsWith("1") ? 5 : 0;
	}
	
	@Override
	void add(String bin) {
		super.add(bin);
		data = data+bin.substring(1);
	}


}