/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is "Simplenlg".
 *
 * The Initial Developer of the Original Code is Ehud Reiter, Albert Gatt and Dave Westwater.
 * Portions created by Ehud Reiter, Albert Gatt and Dave Westwater are Copyright (C) 2010-11 The University of Aberdeen. All Rights Reserved.
 *
 * Contributor(s): Ehud Reiter, Albert Gatt, Dave Wewstwater, Roman Kutlak, Margaret Mitchell.
 */
package simplenlg.format.english;

import java.util.ArrayList;
import java.util.List;

import simplenlg.framework.DocumentCategory;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.ElementCategory;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGModule;
import simplenlg.framework.StringElement;

/**
 * <p>
 * This processing module adds some simple plain text formatting to the
 * SimpleNLG output. This includes the following:
 * <ul>
 * <li>Adding the document title to the beginning of the text.</li>
 * <li>Adding section titles in the relevant places.</li>
 * <li>Adding appropriate new line breaks for ease-of-reading.</li>
 * <li>Indenting list items with ' * '.</li>
 * </ul>
 * </p>
 * 
 * <hr>
 * 
 * <p>
 * Copyright (C) 2010, University of Aberdeen
 * </p>
 * 
 * <p>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * </p>
 * 
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * </p>
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License in
 * the zip file. If not, see <a
 * href="http://www.gnu.org/licenses/">www.gnu.org/licenses</a>.
 * </p>
 * 
 * <p>
 * For more details on SimpleNLG visit the project website at <a
 * href="http://www.csd.abdn.ac.uk/research/simplenlg/"
 * >www.csd.abdn.ac.uk/research/simplenlg</a> or email Dr Ehud Reiter at
 * e.reiter@abdn.ac.uk
 * </p>
 * 
 * @author D. Westwater, University of Aberdeen.
 * @version 4.0
 * 
 */
public class TextFormatter extends NLGModule {

	@Override
	public void initialise() {
		// Do nothing
	}

	@Override
	public NLGElement realise(NLGElement element) {
		NLGElement realisedComponent = null;

		StringBuffer realisation = new StringBuffer();
		if (element != null) {
			ElementCategory category = element.getCategory();
			if (category instanceof DocumentCategory
					&& element instanceof DocumentElement) {
				List<NLGElement> components = ((DocumentElement) element)
						.getComponents();
				String title = ((DocumentElement) element).getTitle();
				switch ((DocumentCategory) category) {
				case DOCUMENT:
				case SECTION:
				case LIST:
					if (title != null) {
						realisation.append(title).append('\n');
					}
					for (NLGElement eachComponent : components) {
						realisedComponent = realise(eachComponent);
						if (realisedComponent != null) {
							realisation.append(realisedComponent
									.getRealisation());
						}
					}
					break;

				case PARAGRAPH:
					if (null != components && 0 < components.size()) {
						realisedComponent = realise(components.get(0));
						if (realisedComponent != null) {
							realisation.append(realisedComponent
									.getRealisation());
						}
						for (int i = 1; i < components.size(); i++) {
							if (realisedComponent != null) {
								realisation.append(' ');
							}
							realisedComponent = realise(components.get(i));
							if (realisedComponent != null) {
								realisation.append(realisedComponent
										.getRealisation());
							}
						}
					}
					realisation.append("\n\n");
					break;

				case SENTENCE:
					realisation.append(element.getRealisation());
					break;

				case LIST_ITEM:
					realisation.append(" * ").append(element.getRealisation()); //$NON-NLS-1$
					break;
				}
			} else if (element instanceof StringElement) {
				realisation.append(element.getRealisation());
			}
		}
		return new StringElement(realisation.toString());
	}

	@Override
	public List<NLGElement> realise(List<NLGElement> elements) {
		List<NLGElement> realisedList = new ArrayList<NLGElement>();

		if (elements != null) {
			for (NLGElement eachElement : elements) {
				realisedList.add(realise(eachElement));
			}
		}
		return realisedList;
	}
}
