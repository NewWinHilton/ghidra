/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.pdb.pdbreader.type;

import ghidra.pdb.PdbByteReader;
import ghidra.pdb.PdbException;
import ghidra.pdb.pdbreader.AbstractPdb;
import ghidra.pdb.pdbreader.AbstractTypeIndex;

/**
 * This class represents various flavors of Index type.
 * <P>
 * Note: we do not necessarily understand each of these data type classes.  Refer to the
 *  base class for more information.
 */
public abstract class AbstractIndexMsType extends AbstractMsType {

	protected AbstractTypeIndex referencedTypeIndex;

	/**
	 * Constructor for this type.
	 * @param pdb {@link AbstractPdb} to which this type belongs.
	 * @param reader {@link PdbByteReader} from which this type is deserialized.
	 * @throws PdbException Upon not enough data left to parse.
	 */
	public AbstractIndexMsType(AbstractPdb pdb, PdbByteReader reader) throws PdbException {
		super(pdb, reader);
		referencedTypeIndex = create();
		parseFields(reader);
	}

	/**
	 * Returns the type index of the referenced type.
	 * @return Type index of the referened type.
	 */
	public int getReferencedIndex() {
		return referencedTypeIndex.get();
	}

	/**
	 * Creates subcomponents for this class, which can be deserialized later.
	 * @return the {@link AbstractTypeIndex} type necessary for the {@link #referencedTypeIndex}
	 * in the concrete class.
	 */
	protected abstract AbstractTypeIndex create();

	/**
	 * Parses fields for this type.
	 * <P>
	 * Implementing class must, in the appropriate order pertinent to itself, parse
	 * {@link #referencedTypeIndex} and optional padding.
	 * @param reader {@link PdbByteReader} from which the fields are parsed.
	 * @throws PdbException Upon not enough data left to parse.
	 */
	protected abstract void parseFields(PdbByteReader reader) throws PdbException;

}
