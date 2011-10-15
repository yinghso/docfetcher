/*******************************************************************************
 * Copyright (c) 2011 Tran Nam Quang.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Tran Nam Quang - initial API and implementation
 *******************************************************************************/

package net.sourceforge.docfetcher.gui.preview;

import net.sourceforge.docfetcher.enums.Img;
import net.sourceforge.docfetcher.model.search.HighlightedString;
import net.sourceforge.docfetcher.util.Event;
import net.sourceforge.docfetcher.util.annotations.NotNull;
import net.sourceforge.docfetcher.util.gui.ToolItemFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Tran Nam Quang
 */
final class TextPreview extends ToolBarForm {
	
	public final Event<Void> evtTextToHtmlBt = new Event<Void>();
	
	@NotNull HighlightingToolBarWithTextViewer toolBarWithTextViewer;
	@NotNull ToolItem htmlBt;
	
	public TextPreview(@NotNull Composite parent) {
		super(parent);
	}
	
	@NotNull
	protected Control createToolBar(@NotNull Composite parent) {
		toolBarWithTextViewer = new HighlightingToolBarWithTextViewer(parent) {
			protected void createToolItems(ToolItemFactory tif) {
				tif.style(SWT.PUSH);
				htmlBt = tif.image(Img.BUILDING_BLOCKS.get())
						.toolTip("use_embedded_html_viewer") // TODO i18n
						.listener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {
								evtTextToHtmlBt.fire(null);
							}
						}).create();
			}
		};
		return toolBarWithTextViewer.getToolBar();
	}
	
	@NotNull
	protected Control createContents(@NotNull Composite parent) {
		// TODO now: put text viewer into a container with margins
		return toolBarWithTextViewer.createTextViewer(parent);
	}
	
	public void clear() {
		toolBarWithTextViewer.clear();
		htmlBt.setEnabled(false);
	}
	
	public void setHtmlButtonEnabled(boolean enabled) {
		htmlBt.setEnabled(enabled);
	}
	
	public void setUseMonoFont(boolean useMonoFont) {
		toolBarWithTextViewer.setUseMonoFont(useMonoFont);
	}

	public void setText(@NotNull HighlightedString string) {
		toolBarWithTextViewer.setText(string);
	}

	public void appendText(@NotNull HighlightedString string) {
		toolBarWithTextViewer.appendText(string);
	}

}
