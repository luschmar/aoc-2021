import java.math.BigInteger;

abstract class BITSPackage {
	protected String binary = "";
	protected String versionAndType;
	protected int version;
	protected int type;

	public BITSPackage(String versionAndType) {
		this.versionAndType = versionAndType;
		this.version = extractVersion(versionAndType);
		this.type = extractType(versionAndType);
	}

	int extractVersion(String versionAndType) {
		var binaryVersion = "0" + versionAndType.substring(0, 3);
		return new BigInteger(binaryVersion, 2).intValue();
	}

	int extractType(String versionAndType) {
		var binaryVersion = "0" + versionAndType.substring(3, 6);
		return new BigInteger(binaryVersion, 2).intValue();
	}

	abstract int nextToRead();

	void add(String bin) {
		binary = binary + bin;
	}

	@Override
	public String toString() {
		return "v:" + version + " t:" + type;
	}
}